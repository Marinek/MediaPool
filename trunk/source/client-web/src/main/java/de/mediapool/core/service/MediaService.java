package de.mediapool.core.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

import de.mediapool.core.domain.Holding;
import de.mediapool.core.domain.MRelated;
import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Participation;
import de.mediapool.core.domain.Product;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.domain.migration.Filme;
import de.mediapool.core.service.grab.DataGrabber;

public class MediaService implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PERSISTENCE_UNIT = "mediamanager";

	private DataGrabber dataGrabber;

	public MediaService() {

	}

	public JPAContainer<Participation> getAllParticipation() {
		return JPAContainerFactory.make(Participation.class, PERSISTENCE_UNIT);
	}

	public BeanItemContainer<Filme> getFilme() {
		BeanItemContainer<Filme> filmEntrys = new BeanItemContainer<Filme>(Filme.class);
		JPAContainer<Filme> filme = JPAContainerFactory.make(Filme.class, PERSISTENCE_UNIT);

		for (Object itemId : filme.getItemIds()) {
			EntityItem<Filme> filmItem = filme.getItem(itemId);
			filmEntrys.addItem(filmItem.getEntity());
		}

		return filmEntrys;
	}

	public BeanItemContainer<MovieEntry> getAllMovieEntries() {
		BeanItemContainer<MovieEntry> movieEntrys = new BeanItemContainer<MovieEntry>(MovieEntry.class);
		JPAContainer<Movie> movies = JPAContainerFactory.make(Movie.class, PERSISTENCE_UNIT);
		for (Object itemId : movies.getItemIds()) {
			EntityItem<Movie> movieItem = movies.getItem(itemId);
			MovieEntry entry = new MovieEntry(movieItem.getEntity());
			movieEntrys.addItem(entry);
		}
		return movieEntrys;
	}

	public BeanItemContainer<MovieProductEntry> getAllMovieProductEntries() {
		BeanItemContainer<MovieProductEntry> productEntrys = new BeanItemContainer<MovieProductEntry>(
				MovieProductEntry.class);
		JPAContainer<Product> products = JPAContainerFactory.make(Product.class, PERSISTENCE_UNIT);
		for (Object itemId : products.getItemIds()) {
			EntityItem<Product> productItem = products.getItem(itemId);
			MovieProductEntry entry = new MovieProductEntry(productItem.getEntity());
			productEntrys.addItem(entry);
		}
		return productEntrys;
	}

	public BeanItemContainer<MovieHoldingEntry> getUserMovieEntrys(MUser muser) {
		BeanItemContainer<MovieHoldingEntry> movieEntryItems = new BeanItemContainer<MovieHoldingEntry>(
				MovieHoldingEntry.class);
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
		List users = q.getResultList();
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

	public BeanItemContainer<MovieProductEntry> searchMovieProducts(String search, String media) {
		BeanItemContainer<MovieProductEntry> movieProductEntries;
		if (eanSearch(search)) {
			movieProductEntries = searchMovieProductsEanDB(search, media);
			if (movieProductEntries.size() == 0) {
				movieProductEntries = searchMovieProductsEanWeb(search, media);
			}
		} else {
			movieProductEntries = searchMovieProductsDB(search, media);
			if (movieProductEntries.size() == 0) {
				movieProductEntries = searchMovieProductsWeb(search, media);
			}
		}
		return movieProductEntries;
	}

	public BeanItemContainer<MovieProductEntry> searchMovieProductsEanWeb(String search, String media) {
		BeanItemContainer<MovieProductEntry> movieProductEntries = new BeanItemContainer<MovieProductEntry>(
				MovieProductEntry.class);
		Product product = getDataGrabber().searchEanProduct(search);
		movieProductEntries.addBean(new MovieProductEntry(product));

		return movieProductEntries;
	}

	public BeanItemContainer<MovieProductEntry> searchMovieProductsEanDB(String search, String media) {
		BeanItemContainer<MovieProductEntry> movieProductEntries = new BeanItemContainer<MovieProductEntry>(
				MovieProductEntry.class);
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

	public BeanItemContainer<MovieProductEntry> searchMovieProductsWeb(String search, String media) {
		BeanItemContainer<MovieProductEntry> movieProductEntries = new BeanItemContainer<MovieProductEntry>(
				MovieProductEntry.class);
		List<Product> productList = getDataGrabber().searchMovieProducts(search, media);
		for (Product product : productList) {
			movieProductEntries.addBean(new MovieProductEntry(product));
		}
		return movieProductEntries;
	}

	public BeanItemContainer<MovieProductEntry> searchMovieProductsDB(String search, String media) {
		BeanItemContainer<MovieProductEntry> movieProductEntries = new BeanItemContainer<MovieProductEntry>(
				MovieProductEntry.class);
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT p FROM Product p where p.movie.title=:title and p.carrier=:carrier");
		q.setParameter("carrier", media);
		q.setParameter("title", search);
		for (Object productEntry : q.getResultList()) {
			movieProductEntries.addBean(new MovieProductEntry((Product) productEntry));
		}
		em.getTransaction().commit();
		return movieProductEntries;
	}

	public BeanItemContainer<Movie> searchMovieEntry(String name) {
		BeanItemContainer<Movie> movieItems = new BeanItemContainer<Movie>(Movie.class);
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

	public void saveMovieHoldingEntry(Item item) {
		BeanItem<MovieHoldingEntry> newMovieEntryItem = (BeanItem<MovieHoldingEntry>) item;
		Holding holding = newMovieEntryItem.getBean().getHolding();
		EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		em.getTransaction().begin();
		em.merge(holding);
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
		em.getTransaction().commit();
	}

	public DataGrabber getDataGrabber() {
		return dataGrabber;
	}

	public void setDataGrabber(DataGrabber dataGrabber) {
		this.dataGrabber = dataGrabber;
	}
}
