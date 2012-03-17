package de.mediapool.core.persistence.core.interfaces;

import org.hibernate.Session;

import de.mediapool.core.persistence.core.PSException;

public interface IPSTransaction {

	public void commit() throws PSException;
	public void rollback() throws PSException;
	
	public Session getSession() throws PSException;
}
