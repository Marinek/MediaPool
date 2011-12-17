package de.mediapool.business.interfaces;

import java.util.List;

import de.mediapool.beans.media.AbstractMediaBean;
import de.mediapool.exceptions.MPExeption;



public interface IMediaService extends IService {
	
	public abstract void createMedia(AbstractMediaBean mediaBean) throws MPExeption;
	public abstract void deleteMedia(AbstractMediaBean mediaBean) throws MPExeption;
	public abstract AbstractMediaBean getMedia(int mediaId) throws MPExeption;
	
	public abstract List<AbstractMediaBean> getAll() throws MPExeption;
	
}
