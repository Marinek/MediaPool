package de.mediapool.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.mediapool.beans.Film;
import de.mediapool.util.SessionTracer;

public class FilmDaoImpl implements FilmDao {
	
	private SessionFactory factory;
	private boolean trace;
	
	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}
	public void setTrace(boolean trace) {
		this.trace = trace;
	}
	
	public void insert(Film film) {
		Session session = this.factory.getCurrentSession();
		if (this.trace)
			SessionTracer.trace("insert", session);
		session.save(film);
	}
	public void update(Film film) {
		Session session = this.factory.getCurrentSession();
		if (this.trace)
			SessionTracer.trace("update", session);
		session.update(film);
	}
	public void delete(int number) {
		Session session = this.factory.getCurrentSession();
		if (this.trace)
			SessionTracer.trace("delete", session);
		session.delete(new Film());
	}
	public Film get(int number) {
		Session session = this.factory.getCurrentSession();
		if (this.trace)
			SessionTracer.trace("get", session);
		return (Film) session.get(Film.class, number);
	}
}
