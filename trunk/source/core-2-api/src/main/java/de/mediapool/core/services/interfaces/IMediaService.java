package de.mediapool.core.services.interfaces;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.exceptions.MPExeption;

public interface IMediaService <T extends AbstractMediaBean> extends IService {
	
	public T createMedia(T abstractMediaBean) throws MPExeption;
	public void deleteMedia(T abstractMediaBean) throws MPExeption;
	public T getMedia(int id) throws MPExeption;
	public void getAllMedia() throws MPExeption;
}
