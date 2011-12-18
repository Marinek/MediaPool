package de.mediapool.core.persistence.core;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import de.mediapool.core.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IValueObject;

public abstract class AbstractDAOImpl<T extends IValueObject> implements IDataAccessObject<T> {

	protected HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void insert(T valueObject) {
		hibernateTemplate.persist(valueObject);
	}

	public void update(T valueObject) {
		hibernateTemplate.update(valueObject);
	}

	public void delete(T valueObject) {
		hibernateTemplate.delete(valueObject);
	}

	public T get(int valueObjectPrimaryKey) {
		T valueObject = (T) hibernateTemplate.get(this.getValueObjectClass(), valueObjectPrimaryKey);
		Hibernate.initialize(valueObject);
		return valueObject ;
	}

}
