package de.mediapool.core.persistence;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

public class PersistenceContext {

	private static PersistenceContext currentContext;

	private BeanFactory beanFactory;

	private PersistenceContext() {
		this.startBeanFactory();
	}

	private void startBeanFactory() {
		beanFactory = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
	}

	public static final PersistenceContext getInstance() {
		if (currentContext == null) {
			currentContext = new PersistenceContext();
		}

		return currentContext;
	}

	public IPSDataAccessObject<? extends IPSValueObject> getDAO(Class<? extends IPSDataAccessObject<?>> daoClass) {
		return this.beanFactory.getBean(daoClass);
	}

	public IPSTransaction getNewTransaction() {
		return this.beanFactory.getBean(IPSTransaction.class);
	}
}
