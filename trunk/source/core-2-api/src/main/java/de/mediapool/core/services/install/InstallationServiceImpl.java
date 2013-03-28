package de.mediapool.core.services.install;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.TestData;
import de.mediapool.core.TestDataBinding;
import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;
import de.mediapool.core.persistence.vo.user.UserVO;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.core.services.interfaces.IAuthService;
import de.mediapool.core.services.interfaces.IInstallationService;
import de.mediapool.core.services.interfaces.IMediaService;
import de.mediapool.core.services.interfaces.IProductService;

public class InstallationServiceImpl implements IInstallationService {

	private static final String MOVIE = "movie";
	private static final String PRODUCT = "product";

	public void installDB() throws MPException {
		installMediaDB();
		installProductDB();
		installUser();

		IAuthService authService = MPLocalService.getInstance().getAuthService();

		UserBean userBean = authService.auth("Test", "Test");

		IMediaService mediaService = MPLocalService.getInstance().getMediaService();

		IProductService productService = MPLocalService.getInstance().getProductService();

		List<MediaBean> movieList = new ArrayList<MediaBean>();

		for (String[] movieData : TestData.getMovies()) {
			MediaBean lMovieBean = mediaService.createNewMedia("movie");
			movieList.add(TestDataBinding.generateTestMovieData(lMovieBean, movieData));
			mediaService.saveMedia(lMovieBean, userBean);
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

	}

	private void installUser() throws MPException {
		UserVO lUserVO = new UserVO();

		lUserVO.setUsername("Test");
		lUserVO.setPassword("Test");
		lUserVO.setEmail("Test@Test.de");

		try {
			UserVO.getDAO().saveOrUpdate(lUserVO);
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte Benutzer nicht anlegen.", e);
		}
	}

	private void installMediaDB() throws MPException {
		// Achtung: Diese Implementierung ist NICHT Designkorrekt.
		// Diese Schicht kommuniziert EIGENTLICH nur mit der BOSchicht!
		// Die Installation sollte aber hier provisiorisch erfolgen, bis
		// eine geeignete Implementiert ist.

		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityType(MOVIE);
		lMediaTypeVO.setDisplayName("Filme und Serien");

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			for (EntityAttributeDefVO lVO : InstallBindingData.getMediaAttributeList(lMediaTypeVO)) {
				EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

	}

	private void installProductDB() throws MPException {
		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityType(PRODUCT);
		lMediaTypeVO.setDisplayName("Produkte");

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			for (EntityAttributeDefVO lVO : InstallBindingData.getProductAttributeList(lMediaTypeVO)) {
				EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

	}

}
