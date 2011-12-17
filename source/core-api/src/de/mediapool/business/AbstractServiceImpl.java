package de.mediapool.business;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.beans.AbstractBean;
import de.mediapool.beans.validation.ValidationResultBean;
import de.mediapool.business.interfaces.IService;
import de.mediapool.exceptions.MPExeption;
import de.mediapool.utils.ValidationUtil;

public abstract class AbstractServiceImpl implements IService {
	
	public List<ValidationResultBean> validate(AbstractBean pBean) throws MPExeption {
		List<ValidationResultBean> validatationResultList = new ArrayList<ValidationResultBean>();
		
		if(pBean == null) {
			validatationResultList.add(ValidationUtil.error("Bean", "Es wurden keine Daten übergeben."));
		}
		
		return validatationResultList;
	}

}
