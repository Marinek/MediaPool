package de.mediapool.core.services.media;

import java.io.Serializable;
import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.AttributeValueBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.media.MediaResultList;
import de.mediapool.core.business.entities.EntityMetaDataManager;
import de.mediapool.core.business.entities.media.BOMedia;
import de.mediapool.core.business.entities.relationship.BOProductMediaRelationship;
import de.mediapool.core.business.search.entities.media.BOMediaSearch;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.services.interfaces.IMediaService;

public class MediaServiceImpl implements IMediaService, Serializable {

	private static final long serialVersionUID = 1L;

	public MediaBean saveMedia(MediaBean abstractMediaBean, UserBean pUserBean) throws MPException {
		BOMedia boInstance = new BOMedia(pUserBean);

		boInstance.setCurrentEntityBean(abstractMediaBean);

		boInstance.save();

		// TODO: Exception bei Validierungsfehler.

		return boInstance.getCurrentEntityBean();
	}

	public void deleteMedia(MediaBean abstractMediaBean, UserBean pUserBean) throws MPException {
		BOMedia boInstance = new BOMedia(abstractMediaBean.getId(), pUserBean);

		boInstance.delete();
	}

	public MediaBean getMedia(UUID id, UserBean pUserBean) throws MPException {
		BOMedia boInstance = new BOMedia(id, pUserBean);

		return boInstance.getCurrentEntityBean();
	}

	public AttributeValueBean<?> createAttribute(String pMediaType, String pAttributeName, String pValue) throws MPException {
		AttributeValueBean<?> lAttribute = EntityMetaDataManager.getInstance().getAttribute(pAttributeName, pMediaType);

		lAttribute.setAttributeValue(pValue);

		return lAttribute;
	}

	public MediaBean createNewMedia(String pMediaType) throws MPException {
		MediaBean lReturnNewMedia = new MediaBean();

		lReturnNewMedia.setEntityType(pMediaType);

		EntityMetaDataManager.getInstance().initialAttributes(lReturnNewMedia);

		return lReturnNewMedia;
	}

	public void setProductForMedia(ProductBean pReferent, MediaBean pChild) throws MPException {
		new BOProductMediaRelationship(null, pReferent).addChild(pChild);
	}

	public MediaResultList getMediaByType(String pType, UserBean pUserBean) throws MPException {
		return new BOMediaSearch(pUserBean).executeSearch(null);
	}

}
