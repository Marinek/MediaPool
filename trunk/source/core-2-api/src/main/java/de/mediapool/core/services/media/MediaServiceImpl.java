package de.mediapool.core.services.media;

import java.util.UUID;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.entity.attributes.AttributedMediaBean;
import de.mediapool.core.beans.entity.attributes.EntityAttributeBean;
import de.mediapool.core.business.entities.attributes.EntityAttributeTypeManager;
import de.mediapool.core.business.entities.media.BOAttributedMedia;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class MediaServiceImpl implements IMediaService<AttributedMediaBean> {

	public AttributedMediaBean saveMedia(AttributedMediaBean abstractMediaBean, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = BOAttributedMedia.getInstance(pUserBean);
		
		boInstance.setCurrentEntityBean(abstractMediaBean);
		
		boInstance.save();
		
		// TODO: Exception bei Validierungsfehler.
		
		return boInstance.getCurrentEntityBean();
	}

	public void deleteMedia(AttributedMediaBean abstractMediaBean, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = BOAttributedMedia.getInstance(abstractMediaBean.getId(), pUserBean);
		
		boInstance.delete();
	}

	public AttributedMediaBean getMedia(UUID id, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = BOAttributedMedia.getInstance(id, pUserBean);
		
		return boInstance.getCurrentEntityBean();
	}

	public void getAllMedia(UserBean pUserBea) throws MPExeption {
	}
	
	public EntityAttributeBean createAttribute (String pMediaType, String pAttributeName, String pValue) throws MPExeption {
		EntityAttributeBean lAttribute =  EntityAttributeTypeManager.getInstance().getAttribute(pAttributeName, pMediaType);
		
		lAttribute.setAttributeValue(pValue);
		
		return lAttribute;
	}

	public AttributedMediaBean createNewMedia(String pMediaType) throws MPExeption {
		AttributedMediaBean lReturnNewMedia = new AttributedMediaBean();
		
		lReturnNewMedia.setEntityType(pMediaType);
		
		EntityAttributeTypeManager.getInstance().initialAttributes(lReturnNewMedia);
		
		return lReturnNewMedia;
	}

}
