package de.mediapool.core.services.media;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.business.media.BOAbstractMedia;
import de.mediapool.core.business.media.BOMediaTypeManager;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class MediaServiceImpl implements IMediaService {

	public AbstractMediaBean createMedia(AbstractMediaBean abstractMediaBean) throws MPExeption {
		BOAbstractMedia boInstance = BOMediaTypeManager.getInstance().getBOInstance(abstractMediaBean.getMediaType());
		
		boInstance.setCurrentMediaBean(abstractMediaBean);
		
		boInstance.save();
		
		// TODO: Exception bei Validierungsfehler.
		
		return boInstance.getCurrentMediaBean();
	}

	public void deleteMedia(AbstractMediaBean abstractMediaBean) throws MPExeption {
		BOAbstractMedia boInstance = BOMediaTypeManager.getInstance().getBOInstance(abstractMediaBean.getId(), abstractMediaBean.getMediaType());
		
		boInstance.delete();
	}

	public AbstractMediaBean getMedia(int id) throws MPExeption {
		BOAbstractMedia boInstance = BOMediaTypeManager.getInstance().getBOInstance(id);
		
		return boInstance.getCurrentMediaBean();
	}

	public void getAllMedia() throws MPExeption {
		// TODO: Aber es kann nun hier rein ;D
	}

}
