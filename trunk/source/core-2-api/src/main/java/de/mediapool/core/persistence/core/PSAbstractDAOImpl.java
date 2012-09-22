package de.mediapool.core.persistence.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysema.query.sql.MySQLTemplates;

import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.core.querybuilder.PSQueryBuilder;

@Component
public abstract class PSAbstractDAOImpl<T extends IPSValueObject> implements IPSDataAccessObject<T> {

	private SessionFactory sessionFactory;

	/**
	 * Wird für Dependency Injection verwendet um die zu vewendete
	 * {@link SessionFactory} hinzuzufügen.
	 * 
	 * @param sessionFactory
	 *            Implementierung von {@link SessionFactory}
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveOrUpdate(T valueObject) throws PSException {
		IPSTransaction lTransaction = PSTransaction.createTransaction();

		this.saveOrUpdate(valueObject, lTransaction);

		lTransaction.commit();
	}

	public void saveOrUpdate(T valueObject, IPSTransaction pTransaction) throws PSException {
		if (pTransaction == null) {
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
		if (pTransaction == null) {
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

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected List<T> findByCriteria(PSCriteria pCriteria) throws PSException {
		if (pCriteria == null) {
			throw new PSException("Criteria dürfen nicht null sein!");
		}

		return this.find(pCriteria);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByBuilder(PSQueryBuilder builder) throws PSException {
		List<T> lReturnList = new ArrayList<T>();

		if (builder == null) {
			throw new PSException("SQL-Builder dürfen nicht null sein!");
		}

		List<Map<String, String>> list = builder.getHibernateQuery().list();

		for (Map<String, String> listItem : list) {
			lReturnList.add(this.getTransientVOInstanceFor(listItem));
		}

		return lReturnList;
	}

	private T getTransientVOInstanceFor(Map<String, String> listItem) throws PSException {
		T lReturnValue = null;
		Class<T> valueObjectClass = this.getValueObjectClass();

		try {
			Constructor<T> constructor = valueObjectClass.getConstructor(Map.class);
			lReturnValue = constructor.newInstance(listItem);
		} catch (Exception e) {
			throw new PSException("Fehler beim Erstellen eines TransientVO", e);
		}
		return lReturnValue;
	}

	protected PSCriteria createCriteria() throws PSException {
		return new PSCriteria(this.getValueObjectClass().getName());
	}

	protected HibernateSQLQuery getHibernateSQLQuery() throws PSException {
		return new HibernateSQLQuery(this.getSession(), new MySQLTemplates());
	}

	protected Session getSession() {
		return this.sessionFactory.openSession();
	}

	protected abstract Class<T> getValueObjectClass() throws PSException;
}
