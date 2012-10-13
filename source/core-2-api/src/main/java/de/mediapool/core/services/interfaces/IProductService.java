package de.mediapool.core.services.interfaces;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.exceptions.MPExeption;

public interface IProductService extends IService {

	public ProductBean createNewProduct() throws MPExeption;

	public void saveProduct(ProductBean abstractProductBean) throws MPExeption;

	public void deleteProduct(ProductBean abstractProductBean, UserBean pUserBean) throws MPExeption;

	public ProductBean getProduct(UUID id, UserBean pUserBean) throws MPExeption;

	public ProductMediaResultList getAllProductMedia(UserBean pUserBean) throws MPExeption;

	public ProductMediaResultList getProductByEan(String ean, UserBean pUserBean) throws MPExeption;

	public ProductMediaResultList getProductByTitle(String title, UserBean pUserBean) throws MPExeption;

	public void addMediaToProduct(MediaBean pReferent, ProductBean pParent) throws MPExeption;
}
