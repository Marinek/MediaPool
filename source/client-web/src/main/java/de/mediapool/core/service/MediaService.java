package de.mediapool.core.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;

import de.mediapool.core.domain.Holding;
import de.mediapool.core.domain.MRelated;
import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Mpresets;
import de.mediapool.core.domain.Participation;
import de.mediapool.core.domain.Product;
import de.mediapool.core.domain.container.MovieContainer;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.domain.container.MovieEntryType;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.domain.migration.Filme;
import de.mediapool.core.service.grab.DataGrabber;

public class MediaService implements Serializable {
	private final Logger logger = LoggerFactory.getLogger(MediaService.class);

	private static final long serialVersionUID = 1L;

	public static final String PERSISTENCE_UNIT = "mediamanager";

	private DataGrabber dataGrabber;

	public MediaService() {

	}

	public JPAContainer<Participation> getAllParticipation() {
		return JPAContainerFactory.make(Participation.class, PERSISTENCE_UNIT);
	}

	private List<Filme> filmMigrationGet() {
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		List<Filme> filmList;
		Query q = em.createQuery("SELECT f FROM Filme f");
		filmList = q.getResultList();
		em.getTransaction().commit();
		return filmList;
	}

	public MovieContainer filmMigrationMerge() {
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		MovieContainer migratedFilms = new MovieContainer(MovieHoldingEntry.class, MovieEntryType.MOVIEHOLDINGENTRY);
		MUser muser = new MUser();
		muser.setEmail("mig@mig.de");
		muser.setPassword("migration");
		muser.setUsername("migration");

		for (Filme film : filmMigrationGet()) {

			Holding holding = new Holding();
			Movie movie = new Movie();
			Product product = new Product();
			product.setMovie(movie);
			holding.setProduct(product);
			holding.setMuser(muser);
			holding.setInventorynumber(film.getFach() + "");
			movie.setGenre(film.getGenre());
			holding.setKnowm("Ja".equalsIgnoreCase(film.getGesehen()) ? "known" : "unknown");
			product.setCarrier(film.getMedium());
			movie.setTitle(film.getName());
			// film.getWertung();
			holding.setSituation(film.getQuali());
			em.merge(holding);

			migratedFilms.addBean(new MovieHoldingEntry(holding));
		}

		em.getTransaction().commit();
		return migratedFilms;
	}

	public MovieContainer getAllMovieEntries() {
		MovieContainer movieEntrys = new MovieContainer(MovieEntry.class, MovieEntryType.MOVIEENTRY);
		JPAContainer<Movie> movies = JPAContainerFactory.make(Movie.class, PERSISTENCE_UNIT);
		for (Object itemId : movies.getItemIds()) {
			EntityItem<Movie> movieItem = movies.getItem(itemId);
			MovieEntry entry = new MovieEntry(movieItem.getEntity());
			movieEntrys.addItem(entry);
		}
		return movieEntrys;
	}

	public MovieContainer getUserMovieEntrys(MUser muser) {
		MovieContainer movieEntryItems = new MovieContainer(MovieHoldingEntry.class, MovieEntryType.MOVIEHOLDINGENTRY);

		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT h FROM Holding h where h.muser=:muser");
		q.setParameter("muser", muser);
		for (Object holding : q.getResultList()) {
			movieEntryItems.addBean(new MovieHoldingEntry((Holding) holding));
		}
		em.getTransaction().commit();
		return movieEntryItems;

	}

	public MUser loginMUser(String email, String password) throws WrongUserException {
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT m FROM MUser m where m.email=:email and m.password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		List<MUser> users = q.getResultList();
		MUser muser = null;
		if (users.size() == 1) {
			muser = (MUser) users.get(0);
		} else {
			throw new WrongUserException();
		}
		em.getTransaction().commit();
		return muser;

	}

	public class WrongUserException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	private boolean eanSearch(String search) {
		boolean eansearch;
		try {
			Long.parseLong(search);
			eansearch = true;
		} catch (NumberFormatException e) {
			eansearch = false;
		}
		return eansearch;

	}

	public MovieContainer searchMovieProducts(String search) {
		MovieContainer movieProductEntries;
		if (eanSearch(search)) {
			movieProductEntries = searchMovieProductsEanDB(search);
			if (movieProductEntries.size() == 0) {
				movieProductEntries = searchMovieProductsEanWeb(search);
			}
		} else {
			movieProductEntries = searchMovieProductsDB(search);
			if (movieProductEntries.size() == 0) {
				movieProductEntries = searchMovieProductsWeb(search);
			}
		}
		return movieProductEntries;
	}

	public MovieContainer searchMovieProductsEanWeb(String search) {
		MovieContainer movieProductEntries = new MovieContainer(MovieProductEntry.class,
				MovieEntryType.MOVIEPRODUCTENTRY);
		Product product = getDataGrabber().searchEanProduct(search);
		movieProductEntries.addBean(new MovieProductEntry(product));

		return movieProductEntries;
	}

	public MovieContainer searchMovieProductsEanDB(String search) {
		MovieContainer movieProductEntries = new MovieContainer(MovieProductEntry.class,
				MovieEntryType.MOVIEPRODUCTENTRY);
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT p FROM Product p where p.ean=:ean");
		q.setParameter("ean", search);
		for (Object productEntry : q.getResultList()) {
			movieProductEntries.addBean(new MovieProductEntry((Product) productEntry));
		}
		em.getTransaction().commit();
		return movieProductEntries;
	}

	public MovieContainer searchMovieProductsWeb(String search) {
		MovieContainer movieProductEntries = new MovieContainer(MovieProductEntry.class,
				MovieEntryType.MOVIEPRODUCTENTRY);
		List<Product> productList = getDataGrabber().searchMovieProducts(search);
		for (Product product : productList) {
			movieProductEntries.addBean(new MovieProductEntry(product));
		}
		return movieProductEntries;
	}

	public MovieContainer searchMovieProductsDB(String search) {
		MovieContainer movieProductEntries = new MovieContainer(MovieProductEntry.class,
				MovieEntryType.MOVIEPRODUCTENTRY);
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT p FROM Product p where p.movie.title=:title");
		q.setParameter("title", search);
		for (Object productEntry : q.getResultList()) {
			movieProductEntries.addBean(new MovieProductEntry((Product) productEntry));
		}
		em.getTransaction().commit();
		return movieProductEntries;
	}

	public List<String> getMpresetsFor(String field) {
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT p FROM Mpresets p where p.field=:field");
		q.setParameter("field", field);
		List<String> values = new ArrayList<String>();
		for (Object mpresets : q.getResultList()) {
			values.add(((Mpresets) mpresets).getValue());
		}
		em.getTransaction().commit();
		return values;
	}

	public MovieContainer searchMovieEntry(String name) {
		MovieContainer movieItems = new MovieContainer(MovieEntry.class, MovieEntryType.MOVIEENTRY);
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT m FROM Movie m where m.title=:title");
		q.setParameter("title", name);
		for (Object movie : q.getResultList()) {
			movieItems.addBean((Movie) movie);
		}
		em.getTransaction().commit();
		return movieItems;

	}

	// TODO implement if Product already exists
	public void saveMovieHoldingEntry(Item item) {
		BeanItem<MovieHoldingEntry> newMovieEntryItem = (BeanItem<MovieHoldingEntry>) item;
		Holding holding = newMovieEntryItem.getBean().getHolding();
		String newUrl = saveImage(holding.getProduct().getImage(), holding.getProduct().getEan());
		holding.getProduct().getMovie().setLocal(true);
		holding.getProduct().setImage(newUrl);
		holding.getProduct().getMovie().setCover(newUrl);
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		em.merge(holding);
		em.getTransaction().commit();

	}

	public void removeMovieHoldingEntry(Item item) {
		BeanItem<MovieHoldingEntry> newMovieEntryItem = (BeanItem<MovieHoldingEntry>) item;
		Holding holding = newMovieEntryItem.getBean().getHolding();
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Holding toRemove = em.merge(holding);
		em.remove(toRemove);
		em.getTransaction().commit();

	}

	public void addProductToUser(Product product, MUser muser) {
		Holding holding = new Holding();
		holding.setProduct(product);
		holding.setMuser(muser);
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		em.merge(holding);
		em.getTransaction().commit();
	}

	final static String actors[] = { "Marlon Brando", "Al Pacino", "Robert DeNiro", "Leonardo DiCaprio" };
	final static String movies[] = { "Der Pate I", "Der Pate II", "Der Pate III", "Inception" };
	final static String covers[] = { "Pate.jpg", "Pate_II.jpg", "Pate_III.jpg", "Inception.jpg" };

	public void createTestData() {

		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

		em.getTransaction().begin();

		MUser muser = new MUser();
		muser.setEmail("test@test.de");
		muser.setPassword("testpass");
		muser.setUsername("matthias");

		MUser muser2 = new MUser();
		muser2.setEmail("test2@test2.de");
		muser2.setPassword("test2pass");
		muser2.setUsername("martin");
		em.persist(muser2);

		Set<MRelated> mrelatedSet = new HashSet<MRelated>();
		MRelated mrelated1 = new MRelated();
		mrelated1.setMuser(muser2);
		mrelatedSet.add(mrelated1);
		muser.setMrelated(mrelatedSet);

		em.persist(muser);

		for (int i = 0; i < 2; i++) {

			Participation part = new Participation();
			part.setMpart("Actor");
			part.setName(actors[i]);

			Movie movie = new Movie();
			movie.setTitle(movies[i]);
			movie.setCover(covers[i]);

			Set<Participation> participations = new HashSet<Participation>();
			participations.add(part);
			movie.setLocal(true);
			movie.setParticipation(participations);

			Product product = new Product();
			product.setCarrier("DVD");
			product.setMovie(movie);
			em.persist(product);

			Holding holding = new Holding();
			holding.setMuser(muser);
			holding.setProduct(product);
			em.persist(holding);
		}

		for (int i = 2; i < 4; i++) {

			Participation part = new Participation();
			part.setMpart("Actor");
			part.setName(actors[i]);

			Movie movie = new Movie();
			movie.setTitle(movies[i]);
			movie.setCover(covers[i]);

			Set<Participation> participations = new HashSet<Participation>();
			participations.add(part);
			movie.setParticipation(participations);
			movie.setLocal(true);
			Product product = new Product();
			product.setCarrier("DVD");
			product.setMovie(movie);
			em.persist(product);

			Holding holding = new Holding();
			holding.setMuser(muser2);
			holding.setProduct(product);
			em.persist(holding);
		}

		Mpresets mpreset = new Mpresets("situation", "new");
		Mpresets mpreset1 = new Mpresets("situation", "used");
		Mpresets mpreset2 = new Mpresets("situation", "unused");
		Mpresets mpreset3 = new Mpresets("known", "good");
		Mpresets mpreset4 = new Mpresets("known", "not good");
		Mpresets mpreset5 = new Mpresets("known", "unknown");

		em.persist(mpreset);
		em.persist(mpreset1);
		em.persist(mpreset2);
		em.persist(mpreset3);
		em.persist(mpreset4);
		em.persist(mpreset5);

		em.getTransaction().commit();
	}

	public DataGrabber getDataGrabber() {
		return dataGrabber;
	}

	public void setDataGrabber(DataGrabber dataGrabber) {
		this.dataGrabber = dataGrabber;
	}

	private String saveImage(String imageUrl, String ean) {
		intialize();
		String localUrl = ean + ".jpg";
		String destinationFile = htdocs_path + localUrl;
		URL url;
		try {
			url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			logger.error("saveimage " + imageUrl);
		}
		return localUrl;
	}

	private boolean intialized = false;
	private String htdocs_path;

	private void intialize() {
		if (!intialized) {
			Configuration config;
			try {
				config = new PropertiesConfiguration("mediapool.properties");
				htdocs_path = config.getString("htdocs_path");
				intialized = true;
			} catch (ConfigurationException e) {
				logger.error(e.getMessage());
			}
		}
	}

}
