package de.mediapool.core.services.interfaces;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.media.MediaType;
import de.mediapool.core.exceptions.MPExeption;

public interface IMediaService extends IService {
	
	public AbstractMediaBean createMedia(AbstractMediaBean abstractMediaBean) throws MPExeption;
	public void deleteMedia(AbstractMediaBean abstractMediaBean) throws MPExeption;
	public AbstractMediaBean getMedia(int id, MediaType mediaType) throws MPExeption;
	public void getAllMedia() throws MPExeption;
}
