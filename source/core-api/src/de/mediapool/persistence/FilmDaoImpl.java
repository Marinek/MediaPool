package de.mediapool.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mediapool.beans.Film;


@Repository("filmDao")
@Transactional
public class FilmDaoImpl implements FilmDao {
	
	private HibernateTemplate hibernateTemplate;
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Transactional(readOnly = false)
	public void insert(Film film) {
		hibernateTemplate.persist(film);
	}
	@Transactional(readOnly = false)
	public void update(Film film) {
		hibernateTemplate.merge(film);
	}
	@Transactional(readOnly = false)
	public void delete(int id) {
		hibernateTemplate.delete(id);
	}
	
	public Film get(int id) {
		Film film = (Film) hibernateTemplate.get(Film.class, id);
		Hibernate.initialize(film);
		return film ;
	}
	
	public List<Film> getAll() {
		System.out.println("FilmDaoImpl.getAll()");
		List<Film> filme = hibernateTemplate.find("from Film");
		return filme;
	}
}
