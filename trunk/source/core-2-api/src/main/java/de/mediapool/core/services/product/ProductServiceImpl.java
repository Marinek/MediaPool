package de.mediapool.core.services.product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.beans.search.entity.joined.ProductMediaSearchBean;
import de.mediapool.core.business.entities.attributes.EntityAttributeTypeManager;
import de.mediapool.core.business.entities.products.BOProduct;
import de.mediapool.core.business.entities.relationship.BOProductMediaRelationship;
import de.mediapool.core.business.search.entities.joined.BOProductMediaSearch;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IProductService;
import de.mediapool.core.utils.AttributeCriteriaUtil;

public class ProductServiceImpl implements IProductService {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public ProductBean createNewProduct() throws MPExeption {
		ProductBean lReturnNewMedia = new ProductBean();

		lReturnNewMedia.setEntityType("product");

		EntityAttributeTypeManager.getInstance().initialAttributes(lReturnNewMedia);

		return lReturnNewMedia;
	}

	public void saveProduct(ProductBean createNewProduct) throws MPExeption {
		BOProduct boInstance = new BOProduct(null);

		boInstance.setCurrentEntityBean(createNewProduct);

		boInstance.save();

	}

	public ProductMediaResultList getAllProductMedia(UserBean pUserBean) throws MPExeption {
		ProductMediaSearchBean lSearchBean = new ProductMediaSearchBean();

		BOProductMediaSearch productMediaSearch = new BOProductMediaSearch(pUserBean);

		ProductMediaResultList pmList = productMediaSearch.executeSearch(lSearchBean);
		return pmList;
	}

	public ProductMediaResultList getProductByEan(String ean, UserBean pUserBean) throws MPExeption {
		ProductMediaSearchBean lSearchBean = new ProductMediaSearchBean();

		lSearchBean.addProductCriteria(AttributeCriteriaUtil.eq("ean", ean));

		BOProductMediaSearch productMediaSearch = new BOProductMediaSearch(pUserBean);

		ProductMediaResultList pmList = productMediaSearch.executeSearch(lSearchBean);
		return pmList;
	}

	public ProductMediaResultList getProductByTitle(String title, UserBean pUserBean) throws MPExeption {
		ProductMediaSearchBean lSearchBean = new ProductMediaSearchBean();

		lSearchBean.addMediaCriteria(AttributeCriteriaUtil.like("title", title));

		BOProductMediaSearch productMediaSearch = new BOProductMediaSearch(pUserBean);

		ProductMediaResultList pmList = productMediaSearch.executeSearch(lSearchBean);
		return pmList;
	}

	public void addMediaToProduct(MediaBean pReferent, ProductBean pParent) throws MPExeption {
		new BOProductMediaRelationship(null, pReferent).addParent(pParent);
	}

	public void deleteProduct(ProductBean abstractProductBean, UserBean pUserBean) throws MPExeption {
		// TODO Auto-generated method stub

	}

	// TODO remove DUMMY Implementation
	public ProductBean getProduct(UUID id, UserBean pUserBean) throws MPExeption {

		List<ProductBean> productList = new ArrayList<ProductBean>();
		String product1[] = { "12345677", "Blue-ray", "cover.jpg", "english", "good", "uncut", "12", "90", "1", "10", "01.01.1980" };
		ProductBean lProductBean = createNewProduct();
		productList.add(generateTestProductData(lProductBean, product1));

		return productList.get(0);
	}

	public ProductBean generateTestProductData(ProductBean lProductBean, String[] product) {

		lProductBean.setName(product[0]);

		lProductBean.setAttribute("ean", product[0]);

		lProductBean.setAttribute("carrier", product[1]);

		lProductBean.setAttribute("cover", product[2]);

		lProductBean.setAttribute("mlanguage", product[3]);

		lProductBean.setAttribute("quality", product[4]);

		lProductBean.setAttribute("special", product[5]);

		lProductBean.setAttribute("approvedage", product[6]);

		lProductBean.setAttribute("duration", product[7]);

		lProductBean.setAttribute("numberdiscs", product[8]);

		lProductBean.setAttribute("price", product[9]);

		lProductBean.setAttribute("launchdate", product[10]);

		return lProductBean;

	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
