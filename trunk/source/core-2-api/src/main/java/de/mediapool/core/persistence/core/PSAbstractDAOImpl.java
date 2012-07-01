package de.mediapool.core.persistence.core;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

public abstract class PSAbstractDAOImpl<T extends IPSValueObject> implements IPSDataAccessObject<T> {

	private SessionFactory  sessionFactory;
	
	/**
	 * Wird für Dependency Injection verwendet um die zu vewendete {@link SessionFactory} 
	 * hinzuzufügen.
	 * 
	 * @param sessionFactory Implementierung von {@link SessionFactory}
	 */
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

	public void delete(T valueObject) throws PSException {
		IPSTransaction lTransaction = PSTransaction.createTransaction();
		
		this.delete(valueObject, lTransaction);
		
		lTransaction.commit();
	}
	
	public void delete(T valueObject, IPSTransaction pTransaction) throws PSException {
		if(pTransaction == null) {
			throw new PSException("Die Transaktion darf nicht null sein.");
		}
		
		Session lSession = pTransaction.getSession();
		
		lSession.delete(valueObject);
	}
	
	public List<T> findAll() throws PSException {
		return this.find(this.createCriteria());
	}
	
	@SuppressWarnings("unchecked")
	private List<T> find(PSCriteria pCriteria) throws PSException {
		Criteria executableCriteria = pCriteria.getExecutableCriteria(this.getSession());
		
		return executableCriteria.list();
	}
	
	protected SessionFactory getSessionFactory () {
		 return sessionFactory;
	}

	protected List<T> findByCriteria(PSCriteria pCriteria) throws PSException {
		if(pCriteria == null) {
			throw new PSException("Criteria dürfen nicht null sein!");
		}
		
		return this.find(pCriteria);
	}
	
	protected PSCriteria createCriteria() throws PSException {
		return new PSCriteria(this.getValueObjectClass().getName());
	}

	protected Session getSession() {
		return  this.sessionFactory.openSession();
	}

	protected abstract Class<T> getValueObjectClass() throws PSException;
}
