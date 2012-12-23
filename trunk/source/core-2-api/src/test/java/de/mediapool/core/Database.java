package de.mediapool.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.EntityResultList;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.core.services.interfaces.IAuthService;
import de.mediapool.core.services.interfaces.IInstallationService;
import de.mediapool.core.services.interfaces.IMediaService;
import de.mediapool.core.services.interfaces.IProductService;

public class Database {

	private static final Logger logger = LoggerFactory.getLogger(Database.class);

	@Test
	public void basicOperationTest() {

		IMediaService mediaService = MPLocalService.getInstance().getMediaService();

		IProductService productService = MPLocalService.getInstance().getProductService();

		IInstallationService installationService = MPLocalService.getInstance().getInstallationService();

		IAuthService authService = MPLocalService.getInstance().getAuthService();

		try {
			installationService.installDB();
			UserBean lUserBean = authService.auth("Test", "Test");

			List<MediaBean> movieList = new ArrayList<MediaBean>();

			for (String[] movieData : TestData.getMovies()) {
				MediaBean lMovieBean = mediaService.createNewMedia("movie");
				movieList.add(TestDataBinding.generateTestMovieData(lMovieBean, movieData));
				mediaService.saveMedia(lMovieBean, lUserBean);
			}

			List<ProductBean> productList = new ArrayList<ProductBean>();

			for (String[] productData : TestData.getProducts()) {
				ProductBean lProductBean = productService.createNewProduct();
				productList.add(TestDataBinding.generateTestProductData(lProductBean, productData));
				productService.saveProduct(lProductBean);
			}

			if (movieList.size() <= productList.size()) {
				for (int i = 0; i < movieList.size(); i++) {
					ProductBean product = productList.get(i);
					MediaBean movie = movieList.get(i);
					mediaService.setProductForMedia(product, movie);
				}
			}

			EntityResultList<? extends AbstractEntityBean> pmList = productService.getAllProductMedia(null);
			Assert.assertTrue("size bigger one", pmList.size() > 0);

			for (AbstractEntityBean lBean : pmList) {
				logger.debug(lBean.toString());
				for (EntityAttributeValueBean lValueBean : lBean.getAttributes()) {
					logger.debug(lValueBean.toString());
				}
			}

		} catch (MPExeption e) {
			logger.error(e.getLocalizedMessage());
		}
	}

}
