package de.mediapool.core.persistence.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;

@Component
@Scope("prototype")
public class PSTransaction implements IPSTransaction {

	private SessionFactory sessionFactory;
	
	private Transaction currentTransaction;
	private Session currentSession;
	
	
	public PSTransaction() throws PSException {
	}

	private void initTransaction() throws PSException {
		if(this.currentSession != null) {
			throw new PSException("Das Wiederbenutzen einer Transaktion ist nicht unterstützt!");
		}
		currentSession = this.sessionFactory.openSession();
		
		currentTransaction = currentSession.beginTransaction();
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) throws PSException {
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
