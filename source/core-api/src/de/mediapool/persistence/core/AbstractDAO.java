package de.mediapool.persistence.core;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public abstract class AbstractDAO<T extends AbstractVO> {

	private static SessionFactory sessionFactory = null;

	private SessionFactory getSessionFactory () {
		if(sessionFactory == null) {
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			config.setProperty("hibernate.connection.url", "jdbc:mysql://www.posterum.de:3306/d00f47d2");
			config.setProperty("hibernate.connection.username", "d00f47d2");
			config.setProperty("hibernate.connection.password", "tgz014vb");
			config.setProperty("hibernate.connection.pool_size", "1");
			config.setProperty("hibernate.connection.autocommit", "true");
			config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
			config.setProperty("hibernate.hbm2ddl.auto", "update");
			config.setProperty("hibernate.show_sql", "true");
			config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
			config.setProperty("hibernate.current_session_context_class", "thread");

			// Add your mapped classes here:
//			config.addAnnotatedClass(Film.class);

			sessionFactory = config.configure().buildSessionFactory();
		}
		
		return sessionFactory;
	}

	private Session getSession () {
		return getSessionFactory().openSession();
	}
	
	protected Criteria getCriteria () {
		return this.getSession().createCriteria(this.getVOClassAsString());
	}
	
	protected abstract String getVOClassAsString();

}
