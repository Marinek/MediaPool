package de.mediapool.core.business.media;

import java.util.List;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.utils.ValidationUtil;

public abstract class BOAbstractMedia extends BusinessObject {

	private AbstractMediaBean currentMediaBean = null;
	
	protected BOAbstractMedia () throws MPExeption {
		super();
	}
	
	protected BOAbstractMedia (int mediaID) throws MPExeption {
		super();
		
		this.init(mediaID);
	}
	
	protected void init(int mediaID) throws MPExeption {
	}

	public void setCurrentMediaBean(AbstractMediaBean abstractMediaBean) throws MPExeption {
		this.currentMediaBean = abstractMediaBean;
		
		this.currentMediaBean.setMediaType(getMediaType());
	}
	
	public AbstractMediaBean getCurrentMediaBean () throws MPExeption {
		return this.currentMediaBean;
	}
	
	public void delete()  throws MPExeption {
		this.protectedDelete();
	}
	

	public List<ValidationResultBean> save() throws MPExeption {
		List<ValidationResultBean> validationResult = this.validate();
		
		if(ValidationUtil.canProceed(validationResult)) {
			this.protectedSave();
		}
		
		return validationResult;
	}

	protected abstract void protectedSave() throws MPExeption;
	
	protected abstract void protectedDelete() throws MPExeption;
	
	protected abstract MediaType getMediaType () throws MPExeption;
	
}