package de.mediapool.core;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.beans.media.AttributedMediaBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class Database {

	@Test
	public void test() {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});

		IMediaService bean = (IMediaService) beanFactory.getBean("movieService");

		
		try {
			AttributedMediaBean lMovieBean = new AttributedMediaBean();
			
			lMovieBean.setName("Mein Supertoller Test");
			
			
			lMovieBean.setAttribute("test", "valueTest2");
			
			bean.createMedia(lMovieBean);
			
		} catch (MPExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}