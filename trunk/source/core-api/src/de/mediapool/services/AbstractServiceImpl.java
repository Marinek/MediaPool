package de.mediapool.services;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.beans.AbstractBean;
import de.mediapool.beans.validation.ValidationResultBean;
import de.mediapool.exceptions.MPExeption;
import de.mediapool.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.persistence.core.interfaces.IValueObject;
import de.mediapool.services.interfaces.IService;
import de.mediapool.utils.ValidationUtil;

public abstract class AbstractServiceImpl implements IService {
	
	protected AbstractBean currentContextualBean;
	
	protected IValueObject currentContextualVO;
	
	protected IDataAccessObject<IValueObject> currentContextualDAO;
	
	public void setContextMediaDAO(IDataAccessObject<IValueObject> contextMediaDAO) {
		this.currentContextualDAO = contextMediaDAO;
	}

	public List<ValidationResultBean> validate() throws MPExeption {
		List<ValidationResultBean> validatationResultList = new ArrayList<ValidationResultBean>();
		
		if(currentContextualBean == null) {
			validatationResultList.add(ValidationUtil.error("Bean", "Es wurden keine Daten übergeben."));
		}
		
		return validatationResultList;
	}

	public void setCurrentContextualBean(AbstractBean currentContextualBean) {
		this.currentContextualBean = currentContextualBean;
		
		this.init();
	}

	protected void init() {
		// subclasses should override this, if they want to load VO Information.
	}

	public AbstractBean getCurrentContextualBean() {
		return currentContextualBean;
	}
	
	protected IDataAccessObject<? extends IValueObject> getCurrentContextualDAO() {
		return this.currentContextualDAO;
	}

	protected void setCurrentContextualVO(IValueObject currentContextualVO) {
		this.currentContextualVO = currentContextualVO;
	}

	protected IValueObject getCurrentContextualVO() {
		return currentContextualVO;
	}

}
