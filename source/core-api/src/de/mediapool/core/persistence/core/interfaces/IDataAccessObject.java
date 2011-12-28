package de.mediapool.core.persistence.core.interfaces;

import de.mediapool.core.persistence.core.DBException;


public interface IDataAccessObject<T extends IValueObject> {

	
	public Class<T> getValueObjectClass();
	
	public  T insert(T valueObject) throws DBException;
	public  void update(T valueObject) throws DBException;
	public  void delete(T valueObject) throws DBException;
	public  T get(int valueObjectPrimaryKey) throws DBException;
}
