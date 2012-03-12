package de.mediapool.core.persistence.core.interfaces;

import org.hibernate.Criteria;

import de.mediapool.core.persistence.core.DBException;


public interface IDataAccessObject<T extends IValueObject> {

	
	public Class<T> getValueObjectClass();
	
	public  T insert(T valueObject) throws DBException;
	public  T update(T valueObject) throws DBException;
	public  void delete(T valueObject) throws DBException;
	
	public Criteria createCriteria() throws DBException;
	
	public void commit() throws DBException;
	public void rollback() throws DBException;
}
