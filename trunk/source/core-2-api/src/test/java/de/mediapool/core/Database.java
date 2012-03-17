package de.mediapool.core;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.beans.media.AttributedMediaBean;
import de.mediapool.core.beans.media.MediaAttributeBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class Database {

	@Test
	public void test() {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});

		IMediaService<AttributedMediaBean> bean = (IMediaService<AttributedMediaBean>) beanFactory.getBean("movieService");

		
		try {
			AttributedMediaBean lMovieBean = new AttributedMediaBean();
			
			MediaAttributeBean lAttribute = bean.createAttribute("Movie", "duration", "123");
			
			lMovieBean.addAttribute(lAttribute);

			
			lMovieBean.setName("Dies ist ein Test.");
			
			bean.createMedia(lMovieBean);
			
		} catch (MPExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
