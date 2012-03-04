package de.mediapool.core.persistence.core;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import de.mediapool.core.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IValueObject;

public abstract class AbstractDAOImpl<T extends IValueObject> implements IDataAccessObject<T> {

	private SessionFactory  sessionFactory;
	
	public SessionFactory getSessionFactory () {
			
		 return sessionFactory;
	}
	
	private Transaction transaction;

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	public T insert(T valueObject) throws DBException {
		Session lSession = this.getSession();
		
		lSession.save(valueObject);
		
		return valueObject;
	}

	public void update(T valueObject) {
		this.getSession().update(valueObject);
	}

	public void delete(T valueObject) {
		this.getSession().delete(valueObject);
	}

	public T get(int valueObjectPrimaryKey) {
		T valueObject = (T) this.getSession().get(this.getValueObjectClass(), valueObjectPrimaryKey);
		Hibernate.initialize(valueObject);
		return valueObject ;
	}

	
	protected Transaction getTransaction(Session pSession) {
		if(this.transaction == null) {
			this.transaction = pSession.beginTransaction();
		}
		
		return this.transaction;
	}
	
	protected Session getSession() {
		return this.sessionFactory.openSession();
	}

	public void commit() throws DBException {
		
	}

	public void rollback() throws DBException {
		
	}
}
