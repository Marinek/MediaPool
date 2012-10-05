package de.mediapool.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
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
			UserBean lUserBean = authService.auth("test", "test");

			installationService.installDB();

			List<MediaBean> movieList = new ArrayList<MediaBean>();

			for (String[] movieData : TestData.getMovies()) {
				MediaBean lMovieBean = mediaService.createNewMedia("movie");
				movieList.add(TestDataBinding.generateTestMovieData(lMovieBean, movieData));
			}

			List<ProductBean> productList = new ArrayList<ProductBean>();

			for (String[] productData : TestData.getProducts()) {
				ProductBean lProductBean = productService.createNewProduct();
				productList.add(TestDataBinding.generateTestProductData(lProductBean, productData));
			}

			for (int i = 0; i < movieList.size(); i++) {
				ProductBean product = productList.get(i);
				MediaBean movie = movieList.get(i);
				mediaService.saveMedia(movie, lUserBean);
				productService.saveProduct(product);
				mediaService.setProductForMedia(product, movie);
			}

			ProductMediaResultList pmList = mediaService.getAllProductMedia(null);
			Assert.assertTrue("size bigger one", pmList.size() > 0);
			logger.info(pmList.get(0).getIdAsString());

		} catch (MPExeption e) {
			logger.error(e.getLocalizedMessage());
		}
	}

}
