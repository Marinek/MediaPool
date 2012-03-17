package de.mediapool.core.persistence.core.interfaces;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;


public interface IPSDataAccessObject<T extends IPSValueObject> {

	
	public Class<T> getValueObjectClass();
	
	public  void saveOrUpdate(T valueObject, IPSTransaction psTransaction) throws PSException;
	public  void saveOrUpdate(T valueObject) throws PSException;
	
	public  void delete(T valueObject) throws PSException;
	
	public List<T> findAll () throws PSException;
	
	public void commit() throws PSException;
	public void rollback() throws PSException;
}
