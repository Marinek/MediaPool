package de.mediapool.core.services.interfaces;

import java.util.List;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.exceptions.MPExeption;

public interface IService {

	public List<ValidationResultBean> validate() throws MPExeption;
	
	public void setCurrentContextualBean(AbstractBean currentContextualBean) throws MPExeption;
	
}
