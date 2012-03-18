package de.mediapool.core.persistence.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;

public class PSTransaction implements IPSTransaction {

	private SessionFactory sessionFactory;
	
	private Transaction currentTransaction;
	private Session currentSession;
	
	
	public PSTransaction() throws PSException {
	}

	private void initTransaction() {
		currentSession = this.sessionFactory.openSession();
		
		currentTransaction = currentSession.beginTransaction();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
		this.initTransaction ();
	}
	
	public Session getSession () throws PSException {
		return this.currentSession;
	}
	
	public void commit() throws PSException {
		this.currentSession.flush();
		
		this.currentTransaction.commit();
		
		this.closeSession();
	}
	
	public void rollback() {
		this.currentTransaction.rollback();
		
		this.closeSession();
	}
	
	private void closeSession() {
		this.currentSession.close();
	}

	
	public static IPSTransaction createTransaction () {
		IPSTransaction lTransaction =  (IPSTransaction) PersistenceContext.getInstance().getNewTransaction();
		
		return lTransaction;
	}
}
