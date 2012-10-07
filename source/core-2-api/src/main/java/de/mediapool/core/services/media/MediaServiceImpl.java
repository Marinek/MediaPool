package de.mediapool.core.services.media;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.beans.search.entity.joined.ProductMediaSearchBean;
import de.mediapool.core.business.entities.attributes.EntityAttributeTypeManager;
import de.mediapool.core.business.entities.media.BOAttributedMedia;
import de.mediapool.core.business.entities.relationship.BOProductMediaRelationship;
import de.mediapool.core.business.search.entities.joined.BOProductMediaSearch;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class MediaServiceImpl implements IMediaService {

	public MediaBean saveMedia(MediaBean abstractMediaBean, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = new BOAttributedMedia(pUserBean);

		boInstance.setCurrentEntityBean(abstractMediaBean);

		boInstance.save();

		// TODO: Exception bei Validierungsfehler.

		return boInstance.getCurrentEntityBean();
	}

	public void deleteMedia(MediaBean abstractMediaBean, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = new BOAttributedMedia(abstractMediaBean.getId(), pUserBean);

		boInstance.delete();
	}

	public MediaBean getMedia(UUID id, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = new BOAttributedMedia(id, pUserBean);

		return boInstance.getCurrentEntityBean();
	}

	public ProductMediaResultList getAllProductMedia(UserBean pUserBean) throws MPExeption {
		ProductMediaSearchBean lSearchBean = new ProductMediaSearchBean();

		// lSearchBean.addProductCriteria(new
		// EntityCriteriaBean(SearchOperation.EQ, new KeyValueBean("ean",
		// "12345678")));
		// lSearchBean.addMediaCriteria(new
		// EntityCriteriaBean(SearchOperation.GT, new KeyValueBean("duration",
		// "120")));

		BOProductMediaSearch productMediaSearch = new BOProductMediaSearch(pUserBean);

		ProductMediaResultList pmList = productMediaSearch.executeSearch(lSearchBean);
		return pmList;
	}

	public EntityAttributeValueBean createAttribute(String pMediaType, String pAttributeName, String pValue) throws MPExeption {
		EntityAttributeValueBean lAttribute = EntityAttributeTypeManager.getInstance().getAttribute(pAttributeName, pMediaType);

		lAttribute.setAttributeValue(pValue);

		return lAttribute;
	}

	public MediaBean createNewMedia(String pMediaType) throws MPExeption {
		MediaBean lReturnNewMedia = new MediaBean();

		lReturnNewMedia.setEntityType(pMediaType);

		EntityAttributeTypeManager.getInstance().initialAttributes(lReturnNewMedia);

		return lReturnNewMedia;
	}

	public void setProductForMedia(ProductBean pReferent, MediaBean pChild) throws MPExeption {
		new BOProductMediaRelationship(null, pReferent).addChild(pChild);
	}

	public void addMediaToProduct(MediaBean pReferent, ProductBean pParent) throws MPExeption {
		new BOProductMediaRelationship(null, pReferent).addParent(pParent);

	}

}
