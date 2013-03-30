package de.mediapool.core.services.interfaces;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.exceptions.MPException;

public interface IMediaService extends IService {

	public MediaBean saveMedia(MediaBean abstractMediaBean, UserBean pUserBean) throws MPException;

	public void deleteMedia(MediaBean abstractMediaBean, UserBean pUserBean) throws MPException;

	public MediaBean getMedia(UUID id, UserBean pUserBean) throws MPException;

	public MediaBean createNewMedia(String pMediaType) throws MPException;

	public ProductMediaResultList getAllMedia(UserBean pUserBean) throws MPException;

	public EntityAttributeValueBean<?> createAttribute(String pMediaType, String pAttributeName, String pValue) throws MPException;

	public void setProductForMedia(ProductBean pReferent, MediaBean pChild) throws MPException;

}
