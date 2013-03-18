package de.mediapool.core.services.interfaces;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.exceptions.MPException;

public interface IProductService extends IService {

	public ProductBean createNewProduct() throws MPException;

	public void saveProduct(ProductBean abstractProductBean) throws MPException;

	public void deleteProduct(ProductBean abstractProductBean, UserBean pUserBean) throws MPException;

	public ProductBean getProduct(UUID id, UserBean pUserBean) throws MPException;

	public ProductMediaResultList getAllProductMedia(UserBean pUserBean) throws MPException;

	public ProductMediaResultList getProductByEan(String ean, UserBean pUserBean) throws MPException;

	public ProductMediaResultList getProductByTitle(String title, UserBean pUserBean) throws MPException;

	public void addMediaToProduct(MediaBean pReferent, ProductBean pParent) throws MPException;
}
