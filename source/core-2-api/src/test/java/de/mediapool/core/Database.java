package de.mediapool.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.AttributedMediaBean;
import de.mediapool.core.beans.business.entity.products.AttributedProductBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IAuthService;
import de.mediapool.core.services.interfaces.IInstallationService;
import de.mediapool.core.services.interfaces.IMediaService;

public class Database {
	

	@Test
	public void test() {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});
		
		
//		AnnotationSessionFactoryBean o = (AnnotationSessionFactoryBean)beanFactory.getBean("&entityManagerFactory");
//		
//		SchemaExport lExpport  = new SchemaExport(o.getConfiguration());
//		
//		lExpport.setOutputFile("database.sql");
//		
//		lExpport.create(true, true);
		
		

		IMediaService<AttributedMediaBean> mediaService = (IMediaService<AttributedMediaBean>) beanFactory.getBean("movieService");

		IInstallationService installationService = (IInstallationService)beanFactory.getBean("installationService");

		IAuthService authService = (IAuthService)beanFactory.getBean("authService");
		
		
		
		
		try {
			UserBean lUserBean = authService.auth("test", "test");
			
			
			installationService.installDB();
			AttributedMediaBean lMovieBean = mediaService.createNewMedia("Movie");

			lMovieBean.setAttribute("duration", "54321");
						
			lMovieBean.setName("Dies dwdwdwdw ein Test.");
			
			AttributedMediaBean lBean = mediaService.saveMedia(lMovieBean, lUserBean);
			
			AttributedMediaBean media = mediaService.getMedia(lBean.getId(), null);
			
			Assert.assertTrue(media != null);
			
			AttributedProductBean createNewProduct = mediaService.createNewProduct();
	
			createNewProduct.setName("Das Produkt des Jahrtausends.");
			
			createNewProduct.setAttribute("ean", "12345678");
			
			mediaService.saveProduct(createNewProduct);
			
			mediaService.addChild(createNewProduct, media);
			
		} catch (MPExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
