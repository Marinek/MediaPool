package de.mediapool.core.service;

import java.io.Serializable;
import java.util.HashSet;
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
import de.mediapool.web.MediapoolApplication;

public class MediaService implements Serializable {

	private static final long serialVersionUID = 1L;

	public MediaService() {

	}

	public static JPAContainer<Participation> getAllParticipation() {
		return JPAContainerFactory.make(Participation.class, MediapoolApplication.PERSISTENCE_UNIT);
	}

	public static BeanItemContainer<MovieEntry> getAllMovieEntries() {
		BeanItemContainer<MovieEntry> movieEntrys = new BeanItemContainer<MovieEntry>(MovieEntry.class);
		JPAContainer<Holding> holdings = JPAContainerFactory.make(Holding.class, MediapoolApplication.PERSISTENCE_UNIT);
		for (Object itemId : holdings.getItemIds()) {
			EntityItem<Holding> holdingItem = holdings.getItem(itemId);

			MovieEntry entry = new MovieEntry(holdingItem.getEntity());
			// BeanItem<MovieEntry> newMovieEntryItem = new
			// BeanItem<MovieEntry>(entry);
			movieEntrys.addItem(entry);
		}

		return movieEntrys;

	}

	public static BeanItemContainer<Movie> searchMovieEntry(String name) {
		BeanItemContainer<Movie> movieItems = new BeanItemContainer<Movie>(Movie.class);
		EntityManager em = Persistence.createEntityManagerFactory("mediamanager").createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT m FROM Movie m where m.title=:title");
		q.setParameter("title", name);
		for (Object movie : q.getResultList()) {
			movieItems.addBean((Movie) movie);
		}
		em.getTransaction().commit();
		return movieItems;

	}

	public static void saveMovieEntry(Item item) {
		BeanItem<MovieEntry> newMovieEntryItem = (BeanItem<MovieEntry>) item;
		Holding holding = newMovieEntryItem.getBean().getHolding();
		EntityManager em = Persistence.createEntityManagerFactory("mediamanager").createEntityManager();
		em.getTransaction().begin();
		em.merge(holding);
		em.getTransaction().commit();
	}

	final static String actors[] = { "Al Pacino", "Rober DeNiro", "Keanu Reeves", "Laurence Fishbourne" };
	final static String movies[] = { "Der Pate I", "Der Pate II", "Matrix", "Matrix II" };

	public static void create() {

		EntityManager em = Persistence.createEntityManagerFactory("mediamanager").createEntityManager();

		em.getTransaction().begin();

		MUser muser = new MUser();
		muser.setEmail("test@test.de");
		muser.setPassword("testpass");
		muser.setUsername("testuser");

		MUser muser2 = new MUser();
		muser2.setEmail("test2@test2.de");
		muser2.setPassword("test2pass");
		muser2.setUsername("test2user");
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

			Set<Participation> participations = new HashSet<Participation>();
			participations.add(part);
			movie.setParticipation(participations);

			Product product = new Product();
			product.setCarrier("DVD");
			product.setMovie(movie);
			em.persist(product);

			Holding holding = new Holding();
			holding.setRating("good");
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

			Set<Participation> participations = new HashSet<Participation>();
			participations.add(part);
			movie.setParticipation(participations);

			Product product = new Product();
			product.setCarrier("DVD");
			product.setMovie(movie);
			em.persist(product);

			Holding holding = new Holding();
			holding.setRating("good");
			holding.setMuser(muser2);
			holding.setProduct(product);
			em.persist(holding);
		}
		em.getTransaction().commit();
	}
}
