package de.mediapool.core.services.interfaces;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.media.attributes.AttributedMediaBean;
import de.mediapool.core.beans.media.attributes.MediaAttributeBean;
import de.mediapool.core.exceptions.MPExeption;

public interface IMediaService <T extends AbstractMediaBean> extends IService {
	
	public T createMedia(T abstractMediaBean, UserBean pUserBean) throws MPExeption;
	public void deleteMedia(T abstractMediaBean, UserBean pUserBean) throws MPExeption;
	public T getMedia(Integer id, UserBean pUserBean) throws MPExeption;
	public void getAllMedia(UserBean pUserBean) throws MPExeption;
	
	public MediaAttributeBean createAttribute (String pMediaType, String pAttributeName, String pValue) throws MPExeption;
	
	public AttributedMediaBean createNewMedia(String pMediaType) throws MPExeption;
}
