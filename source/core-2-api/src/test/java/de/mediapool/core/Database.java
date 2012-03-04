package de.mediapool.core;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.beans.media.MovieBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class Database {

	@Test
	public void test() {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});

		IMediaService bean = (IMediaService) beanFactory.getBean("movieService");

		
		try {
			MovieBean lMovieBean = new MovieBean();
			
			lMovieBean.setLength(123);
			lMovieBean.setName("Mein Supertoller Test");
			
			
			bean.createMedia(lMovieBean);
			
		} catch (MPExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("dsd");
	}
}
