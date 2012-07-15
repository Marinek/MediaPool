package de.mediapool.core.services.interfaces;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.exceptions.MPExeption;

public interface IMediaService <T extends AbstractEntityBean> extends IService {
	
	public T saveMedia(T abstractMediaBean, UserBean pUserBean) throws MPExeption;
	public void deleteMedia(T abstractMediaBean, UserBean pUserBean) throws MPExeption;
	public T getMedia(UUID id, UserBean pUserBean) throws MPExeption;
	public void getAllMedia(UserBean pUserBean) throws MPExeption;
	
	public EntityAttributeValueBean createAttribute (String pMediaType, String pAttributeName, String pValue) throws MPExeption;
	
	public MediaBean createNewMedia(String pMediaType) throws MPExeption;

	public ProductBean createNewProduct() throws MPExeption;
	
	public void addChild(ProductBean pReferent, T pChild ) throws MPExeption;
	public void addParent(T pReferent, ProductBean pParent ) throws MPExeption;
	
	public void saveProduct(ProductBean createNewProduct) throws MPExeption;
}
