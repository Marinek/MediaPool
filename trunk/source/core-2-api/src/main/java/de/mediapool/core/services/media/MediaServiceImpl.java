package de.mediapool.core.services.media;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.media.MediaSearchBean;
import de.mediapool.core.business.entities.attributes.EntityAttributeTypeManager;
import de.mediapool.core.business.entities.media.BOAttributedMedia;
import de.mediapool.core.business.entities.products.BOMediaProduct;
import de.mediapool.core.business.entities.relationship.BOMediaRelationship;
import de.mediapool.core.business.search.entities.media.BOMediaSearch;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class MediaServiceImpl implements IMediaService<MediaBean> {

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

	public void getAllMedia(UserBean pUserBea) throws MPExeption {
		new BOMediaSearch(null).executeSearch(new MediaSearchBean());
	}
	
	public EntityAttributeValueBean createAttribute (String pMediaType, String pAttributeName, String pValue) throws MPExeption {
		EntityAttributeValueBean lAttribute =  EntityAttributeTypeManager.getInstance().getAttribute(pAttributeName, pMediaType);
		
		lAttribute.setAttributeValue(pValue);
		
		return lAttribute;
	}

	public MediaBean createNewMedia(String pMediaType) throws MPExeption {
		MediaBean lReturnNewMedia = new MediaBean();
		
		lReturnNewMedia.setEntityType(pMediaType);
		
		EntityAttributeTypeManager.getInstance().initialAttributes(lReturnNewMedia);
		
		return lReturnNewMedia;
	}

	public void addChild(ProductBean pReferent, MediaBean pChild) throws MPExeption {
		new BOMediaRelationship(null, pReferent).addChild(pChild);
	}

	public void addParent(MediaBean pReferent, ProductBean pParent) throws MPExeption {
		new BOMediaRelationship(null, pReferent).addParent(pParent);
		
	}

	public ProductBean createNewProduct() throws MPExeption {
		ProductBean lReturnNewMedia = new ProductBean();
		
		lReturnNewMedia.setEntityType("Product");
		
		EntityAttributeTypeManager.getInstance().initialAttributes(lReturnNewMedia);
		
		return lReturnNewMedia;
	}

	public void saveProduct(ProductBean createNewProduct) throws MPExeption {
		BOMediaProduct boInstance = new BOMediaProduct(null);
		
		boInstance.setCurrentEntityBean(createNewProduct);
		
		boInstance.save();
		
		// TODO: Exception bei Validierungsfehler.
		
	}

}
