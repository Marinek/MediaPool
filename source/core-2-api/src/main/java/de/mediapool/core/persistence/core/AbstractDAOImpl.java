package de.mediapool.core.persistence.core;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import de.mediapool.core.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IValueObject;

public abstract class AbstractDAOImpl<T extends IValueObject> implements IDataAccessObject<T> {

	private SessionFactory  sessionFactory;
	
	private Session currentSession = null;
	
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
		
		lSession.flush();
		
		return valueObject;
	}

	public T update(T valueObject) {
		Session lSession = this.getSession();
		
		lSession.saveOrUpdate(valueObject);
		
		lSession.flush();
		
		return valueObject;
	}

	public void delete(T valueObject) {
		this.getSession().delete(valueObject);
	}

	protected Transaction getTransaction(Session pSession) {
		if(this.transaction == null) {
			this.transaction = pSession.beginTransaction();
		}
		
		return this.transaction;
	}
	
	public Criteria createCriteria() throws DBException {
		return this.getSession().createCriteria(this.getValueObjectClass());
	}

	protected Session getSession() {
		if(this.currentSession == null ) {
			this.currentSession =  this.sessionFactory.openSession();
		}
		return this.currentSession;
	}

	public void commit() throws DBException {
		
	}

	public void rollback() throws DBException {
		
	}
}
