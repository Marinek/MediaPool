package de.mediapool.core.persistence;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.core.persistence.core.interfaces.IValueObject;


public class PersistenceContext {

	private static PersistenceContext currentContext;
	
	private BeanFactory beanFactory;
	
	private PersistenceContext () {
		this.startBeanFactory ();
	}

	private void startBeanFactory() {
		beanFactory = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});
	}
	
	public static final PersistenceContext getInstance() {
		if(currentContext == null) {
			currentContext = new PersistenceContext();
		}
		
		return currentContext;
	}
	
	public IDataAccessObject<? extends IValueObject> getDAO(Class<? extends IDataAccessObject<?>> daoClass) {
		return this.beanFactory.getBean(daoClass);
	}
}
