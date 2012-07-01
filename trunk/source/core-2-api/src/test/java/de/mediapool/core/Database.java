package de.mediapool.core;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.entity.attributes.AttributedMediaBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class Database {
	

	@Test
	public void test() {
		
		
		
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});
		
		
		AnnotationSessionFactoryBean o = (AnnotationSessionFactoryBean)beanFactory.getBean("&entityManagerFactory");
		
		SchemaExport lExpport  = new SchemaExport(o.getConfiguration());
		
		lExpport.setOutputFile("database.sql");
		
		lExpport.create(true, true);
		
		

		IMediaService<AttributedMediaBean> mediaService = (IMediaService<AttributedMediaBean>) beanFactory.getBean("movieService");

		
		
		
		UserBean lUserBean = new UserBean();
		
		try {
			AttributedMediaBean lMovieBean = mediaService.createNewMedia("Movie");

			lMovieBean.setAttribute("duration", "12345");
			
			lMovieBean.setName("Dies ist ein Test.");
			
			mediaService.createMedia(lMovieBean, lUserBean);
			
		} catch (MPExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
