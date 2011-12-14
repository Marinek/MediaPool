package de.mediapool.persistence.core.interfaces;


public interface IDataAccessObject<T extends IValueObject> {

	public Class<T> getValueObjectClass();
	
	public  void insert(T valueObject);
	public  void update(T valueObject);
	public  void delete(T valueObject);
	public  T get(int valueObjectPrimaryKey);
}
