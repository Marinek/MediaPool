package de.mediapool.test.core;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public abstract class DatabaseAccessTest {

	protected BeanFactory beanFactory;
	
	@Before
	public void initTest() {
	
		beanFactory = new ClassPathXmlApplicationContext(
		        new String[] {"spring.xml"});
	}
	
	public void closeTest() {
	}
}
