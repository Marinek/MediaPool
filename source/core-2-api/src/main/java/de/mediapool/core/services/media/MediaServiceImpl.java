package de.mediapool.core.services.media;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.media.AttributedMediaBean;
import de.mediapool.core.beans.media.MediaAttributeBean;
import de.mediapool.core.beans.media.attributes.MediaAttributeTypeBean;
import de.mediapool.core.business.media.BOAttributedMedia;
import de.mediapool.core.business.media.attributes.MediaAttributeTypeManager;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class MediaServiceImpl implements IMediaService<AttributedMediaBean> {

	public AttributedMediaBean createMedia(AttributedMediaBean abstractMediaBean, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = BOAttributedMedia.getInstance(pUserBean);
		
		boInstance.setCurrentMediaBean(abstractMediaBean);
		
		boInstance.save();
		
		// TODO: Exception bei Validierungsfehler.
		
		return boInstance.getCurrentMediaBean();
	}

	public void deleteMedia(AttributedMediaBean abstractMediaBean, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = BOAttributedMedia.getInstance(abstractMediaBean.getId(), pUserBean);
		
		boInstance.delete();
	}

	public AttributedMediaBean getMedia(int id, UserBean pUserBean) throws MPExeption {
		BOAttributedMedia boInstance = BOAttributedMedia.getInstance(id, pUserBean);
		
		return boInstance.getCurrentMediaBean();
	}

	public void getAllMedia(UserBean pUserBea) throws MPExeption {
	}
	
	public MediaAttributeBean createAttribute (String pMediaType, String pAttributeName, String pValue) throws MPExeption {
		MediaAttributeBean lAttribute = new MediaAttributeBean();
		
		MediaAttributeTypeBean attributeType = MediaAttributeTypeManager.getInstance().getAttribute(pAttributeName, pMediaType);
		
		lAttribute.setAttributeType(attributeType);
		
		lAttribute.setAttributeValue(pValue);
		
		return lAttribute;
	}

}
