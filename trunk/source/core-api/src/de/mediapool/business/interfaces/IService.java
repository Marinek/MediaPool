package de.mediapool.business.interfaces;

import java.util.List;

import de.mediapool.beans.AbstractBean;
import de.mediapool.beans.validation.ValidationResultBean;
import de.mediapool.exceptions.MPExeption;
import de.mediapool.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.persistence.core.interfaces.IValueObject;

public interface IService {

	public List<ValidationResultBean> validate(AbstractBean pBean) throws MPExeption;
	
	public IDataAccessObject<? extends IValueObject> getDAO();
}
