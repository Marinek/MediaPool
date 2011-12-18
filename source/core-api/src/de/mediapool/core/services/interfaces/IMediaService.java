package de.mediapool.core.services.interfaces;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.exceptions.MPExeption;



public interface IMediaService extends IService {
	
	public AbstractMediaBean createMedia() throws MPExeption;
	public void deleteMedia() throws MPExeption;
}
