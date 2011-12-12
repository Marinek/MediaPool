package de.mediapool.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import de.mediapool.beans.Film;


@Repository
public class FilmDaoImpl implements FilmDao {
	
	@PersistenceContext
	private EntityManager em;

	
	public void insert(Film film) {
		em.persist(film);
	}
	public void update(Film film) {
		em.merge(film);
	}
	public void delete(int id) {
		em.remove(id);
	}
	public Film get(int id) {
		Film film = em.find(Film.class, id);
		Hibernate.initialize(film);
		return film ;
	}
	
	public List<Film> getAll() {
		List<Film> filme = em.createQuery("from Film").getResultList();
		return filme;
	}
}
