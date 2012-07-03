package de.mediapool.core.services.interfaces;

import java.util.UUID;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.entity.AbstractEntityBean;
import de.mediapool.core.beans.entity.attributes.AttributedMediaBean;
import de.mediapool.core.beans.entity.attributes.EntityAttributeBean;
import de.mediapool.core.exceptions.MPExeption;

public interface IMediaService <T extends AbstractEntityBean> extends IService {
	
	public T saveMedia(T abstractMediaBean, UserBean pUserBean) throws MPExeption;
	public void deleteMedia(T abstractMediaBean, UserBean pUserBean) throws MPExeption;
	public T getMedia(UUID id, UserBean pUserBean) throws MPExeption;
	public void getAllMedia(UserBean pUserBean) throws MPExeption;
	
	public EntityAttributeBean createAttribute (String pMediaType, String pAttributeName, String pValue) throws MPExeption;
	
	public AttributedMediaBean createNewMedia(String pMediaType) throws MPExeption;
}
