package de.mediapool.core;

import java.util.List;

import org.junit.Test;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.core.services.interfaces.IAuthService;
import de.mediapool.core.services.interfaces.IInstallationService;
import de.mediapool.core.services.interfaces.IMediaService;
import de.mediapool.core.services.interfaces.IProductService;

public class Database {

	@Test
	public void basicOperationTest() {

		IMediaService mediaService = MPLocalService.getInstance().getMediaService();

		IProductService productService = MPLocalService.getInstance().getProductService();

		IInstallationService installationService = MPLocalService.getInstance().getInstallationService();

		IAuthService authService = MPLocalService.getInstance().getAuthService();

		try {
			UserBean lUserBean = authService.auth("test", "test");

			installationService.installDB();
			MediaBean lMovieBean = mediaService.createNewMedia("movie");
			ProductBean lProductBean = productService.createNewProduct();

			List<MediaBean> movieList = TestData.generateTestMovieData(lMovieBean);
			List<ProductBean> productList = TestData.generateTestProductData(lProductBean);

			for (int i = 0; i < movieList.size(); i++) {
				ProductBean product = productList.get(i);
				MediaBean movie = movieList.get(i);
				mediaService.saveMedia(movie, lUserBean);
				productService.saveProduct(product);
				mediaService.setProductForMedia(product, movie);
			}

			mediaService.getAllMedia(null);

		} catch (MPExeption e) {
			e.printStackTrace();
		}
	}

}
