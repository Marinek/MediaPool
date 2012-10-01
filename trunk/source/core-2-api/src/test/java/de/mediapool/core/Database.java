package de.mediapool.core;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IAuthService;
import de.mediapool.core.services.interfaces.IInstallationService;
import de.mediapool.core.services.interfaces.IMediaService;

public class Database {

	@Test
	public void test() {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });

		// AnnotationSessionFactoryBean o =
		// (AnnotationSessionFactoryBean)beanFactory.getBean("&entityManagerFactory");
		//
		// SchemaExport lExpport = new SchemaExport(o.getConfiguration());
		//
		// lExpport.setOutputFile("database.sql");
		//
		// lExpport.create(true, true);

		IMediaService<MediaBean> mediaService = (IMediaService<MediaBean>) beanFactory.getBean("movieService");

		IInstallationService installationService = (IInstallationService) beanFactory.getBean("installationService");

		IAuthService authService = (IAuthService) beanFactory.getBean("authService");

		try {
			UserBean lUserBean = authService.auth("test", "test");

			installationService.installDB();
			MediaBean lMovieBean = mediaService.createNewMedia("movie");
			ProductBean lProductBean = mediaService.createNewProduct();

			List<MediaBean> movieList = TestData.generateTestMovieData(lMovieBean);
			List<ProductBean> productList = TestData.generateTestProductData(lProductBean);

			for (int i = 0; i < movieList.size(); i++) {
				ProductBean product = productList.get(i);
				MediaBean movie = movieList.get(i);
				mediaService.saveMedia(movie, lUserBean);
				mediaService.saveProduct(product);
				mediaService.addChild(product, movie);
			}

			// MediaBean lMovieBean = mediaService.createNewMedia("movie");
			//
			// lMovieBean.setAttribute("duration", "54321");
			//
			// lMovieBean.setName("Dies dwdwdwdw ein Test.");
			//
			// MediaBean lBean = mediaService.saveMedia(lMovieBean, lUserBean);
			//
			// MediaBean media = mediaService.getMedia(lBean.getId(), null);
			//
			// Assert.assertTrue(media != null &&
			// lBean.getId().equals(media.getId()));

			// ProductBean createNewProduct = mediaService.createNewProduct();
			//
			// createNewProduct.setName("Das Produkt des Jahrtausends.");
			//
			// createNewProduct.setAttribute("ean", "12345678");
			//
			// mediaService.saveProduct(createNewProduct);
			//
			// mediaService.addChild(createNewProduct, media);

			// mediaService.getAllMedia(null);

		} catch (MPExeption e) {
			e.printStackTrace();
		}

	}
}
