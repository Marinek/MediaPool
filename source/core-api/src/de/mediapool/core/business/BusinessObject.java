package de.mediapool.core.business;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.exceptions.MPExeption;

public abstract class BusinessObject {

	
	public List<ValidationResultBean> validate() throws MPExeption {
		List<ValidationResultBean> validatationResultList = new ArrayList<ValidationResultBean>();
		
		return validatationResultList;
	}
}
