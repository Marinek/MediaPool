package de.mediapool.core.persistence.core.interfaces;

import java.util.List;

import de.mediapool.core.persistence.core.DBException;


public interface IDataAccessObject<T extends IValueObject> {

	
	public Class<T> getValueObjectClass();
	
	public  T insert(T valueObject) throws DBException;
	public  T update(T valueObject) throws DBException;
	public  void delete(T valueObject) throws DBException;
	
	public List<T> findAll () throws DBException;
	
	public void commit() throws DBException;
	public void rollback() throws DBException;
}
