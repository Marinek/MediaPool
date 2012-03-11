package de.mediapool.core.services.media;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.business.media.BOAbstractMedia;
import de.mediapool.core.business.media.BOAttributedMedia;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class MediaServiceImpl implements IMediaService {

	public AbstractMediaBean createMedia(AbstractMediaBean abstractMediaBean) throws MPExeption {
		BOAbstractMedia boInstance = BOAttributedMedia.getInstance();
		
		boInstance.setCurrentMediaBean(abstractMediaBean);
		
		boInstance.save();
		
		// TODO: Exception bei Validierungsfehler.
		
		return boInstance.getCurrentMediaBean();
	}

	public void deleteMedia(AbstractMediaBean abstractMediaBean) throws MPExeption {
		BOAbstractMedia boInstance = BOAttributedMedia.getInstance(abstractMediaBean.getId());
		
		boInstance.delete();
	}

	public AbstractMediaBean getMedia(int id) throws MPExeption {
		BOAbstractMedia boInstance = BOAttributedMedia.getInstance(id);
		
		return boInstance.getCurrentMediaBean();
	}

	public void getAllMedia() throws MPExeption {
	}

}
