package de.mediapool.services.interfaces;

import de.mediapool.beans.media.AbstractMediaBean;
import de.mediapool.exceptions.MPExeption;



public interface IMediaService extends IService {
	
	public AbstractMediaBean createMedia() throws MPExeption;
	public void deleteMedia() throws MPExeption;
}
