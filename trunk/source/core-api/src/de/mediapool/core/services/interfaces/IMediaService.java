package de.mediapool.core.services.interfaces;

import java.util.List;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.persistence.vo.media.MediaVO;



public interface IMediaService extends IService {
	
	public AbstractMediaBean createMedia() throws MPExeption;
	public void deleteMedia() throws MPExeption;
	public void getAllMedia() throws MPExeption;
	public void getMedia(int id) throws MPExeption;
}
