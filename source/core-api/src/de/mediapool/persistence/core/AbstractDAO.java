package de.mediapool.persistence.core;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDAO<T extends AbstractVO> {

	private static SessionFactory sessionFactory = null;

	private SessionFactory getSessionFactory () {
		return null;
	}

	private Session getSession () {
		return getSessionFactory().openSession();
	}
	
	protected Criteria getCriteria () {
		return this.getSession().createCriteria(this.getVOClassAsString());
	}
	
	protected abstract String getVOClassAsString();

}
