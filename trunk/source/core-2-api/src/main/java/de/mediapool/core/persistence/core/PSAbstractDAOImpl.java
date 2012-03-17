package de.mediapool.core.persistence.core;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

public abstract class PSAbstractDAOImpl<T extends IPSValueObject> implements IPSDataAccessObject<T> {

	private SessionFactory  sessionFactory;
	
	private Transaction transaction;

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void saveOrUpdate(T valueObject) throws PSException {
		IPSTransaction lTransaction = PSTransaction.createTransaction();
		
		this.saveOrUpdate(valueObject, lTransaction);
		
		lTransaction.commit();
	}

	public void saveOrUpdate(T valueObject, IPSTransaction pTransaction) throws PSException {
		if(pTransaction == null) {
			throw new PSException("Die Transaktion darf nicht null sein.");
		}
		
		Session lSession = pTransaction.getSession();
		
		lSession.saveOrUpdate(valueObject);
		
		lSession.flush();
	}

	public void delete(T valueObject) {
		this.getSession().delete(valueObject);
	}
	
	protected List<T> findByCriteria(PSCriteria pCriteria) throws PSException {
		if(pCriteria == null) {
			throw new PSException("Criteria dürfen nicht null sein!");
		}
		
		return this.find(pCriteria);
	}
	
	public List<T> findAll() throws PSException {
		return this.find(this.createCriteria());
	}
	
	private List<T> find(PSCriteria pCriteria) throws PSException {
		Criteria executableCriteria = pCriteria.getExecutableCriteria(this.getSession());
		
		return executableCriteria.list();
	}
	
	protected SessionFactory getSessionFactory () {
		 return sessionFactory;
	}

	protected Transaction getTransaction(Session pSession) {
		if(this.transaction == null) {
			this.transaction = pSession.beginTransaction();
		}
		
		return this.transaction;
	}
	
	protected PSCriteria createCriteria() throws PSException {
		return new PSCriteria(this.getValueObjectClass().getName());
	}

	protected Session getSession() {
		return  this.sessionFactory.openSession();
	}

	public void commit() throws PSException {
		
	}

	public void rollback() throws PSException {
		
	}
}
