package de.mediapool.services.interfaces;

import java.util.List;

import de.mediapool.beans.AbstractBean;
import de.mediapool.beans.validation.ValidationResultBean;
import de.mediapool.exceptions.MPExeption;

public interface IService {

	public List<ValidationResultBean> validate() throws MPExeption;
	
	public void setCurrentContextualBean(AbstractBean currentContextualBean) throws MPExeption;
	
}
