package de.mediapool.core.services.product;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.keyvalue.string.KeyValueBean;
import de.mediapool.core.beans.search.SearchOperation;
import de.mediapool.core.beans.search.entity.EntityCriteriaBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.beans.search.entity.joined.ProductMediaSearchBean;
import de.mediapool.core.business.entities.attributes.EntityAttributeTypeManager;
import de.mediapool.core.business.entities.products.BOMediaProduct;
import de.mediapool.core.business.entities.relationship.BOProductMediaRelationship;
import de.mediapool.core.business.search.entities.joined.BOProductMediaSearch;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IProductService;

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
		BOMediaProduct boInstance = new BOMediaProduct(null);

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

		lSearchBean.addProductCriteria(new EntityCriteriaBean(SearchOperation.EQ, new KeyValueBean("ean", ean)));

		BOProductMediaSearch productMediaSearch = new BOProductMediaSearch(pUserBean);

		ProductMediaResultList pmList = productMediaSearch.executeSearch(lSearchBean);
		return pmList;
	}

	public ProductMediaResultList getProductByTitle(String title, UserBean pUserBean) throws MPExeption {
		ProductMediaSearchBean lSearchBean = new ProductMediaSearchBean();

		lSearchBean.addMediaCriteria(new EntityCriteriaBean(SearchOperation.LIKE, new KeyValueBean("title", title)));

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

	public ProductBean getProduct(UUID id, UserBean pUserBean) throws MPExeption {
		// TODO Auto-generated method stub
		return null;
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
