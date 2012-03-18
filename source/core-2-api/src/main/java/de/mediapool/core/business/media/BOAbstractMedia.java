package de.mediapool.core.business.media;

import java.util.List;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.utils.PersistenceUtils;
import de.mediapool.core.beans.validation.ValidationErrorType;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.media.MediaVO;
import de.mediapool.core.utils.ValidationUtil;

public abstract class BOAbstractMedia<T extends AbstractMediaBean> extends BusinessObject {

	protected T currentMediaBean = null;
	
	protected MediaVO currentMediaVO = null;

	protected BOAbstractMedia () throws MPExeption {
		super();
	}

	protected BOAbstractMedia (int mediaID) throws MPExeption {
		super();

		this.init(mediaID);
	}

	protected void init(int mediaID) throws MPExeption {
		try {
			currentMediaVO = MediaVO.getDAO().getByPrimaryKey(mediaID);
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Could not Read MovieVO", e);
		}
	}

	public void setCurrentMediaBean(T abstractMediaBean) throws MPExeption {
		this.currentMediaBean = abstractMediaBean;
	}

	public T getCurrentMediaBean () throws MPExeption {
		return this.currentMediaBean;
	}

	public void delete()  throws MPExeption {
		MediaVO deleteMovie = this.getMediaVO();

		try {
			MediaVO.getDAO().delete(deleteMovie, this.getTransaction());
			this.protectedDelete();
			
			this.doCommit();
		} catch (PSException e) {
			this.doRollback();
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_DELETE, "Could not Delete MediaVO", e);
		}
	}


	public List<ValidationResultBean> save() throws MPExeption {
		List<ValidationResultBean> validationResult = this.validate();

		if(ValidationUtil.canProceed(validationResult)) {
			this.currentMediaVO = this.getMediaVO();

			try {
				MediaVO.getDAO().saveOrUpdate(currentMediaVO, this.getTransaction());
				this.protectedSave();
				
				this.doCommit();
			} catch (PSException e) {
				this.doRollback();
				
				throw new MPTechnicalExeption(ExeptionErrorCode.DB_UPDATE, "Could not Update MediaVO", e);
			}
		}
		return validationResult;
	}
	
	public List<ValidationResultBean> validate() throws MPExeption {
		List<ValidationResultBean> lValidation = super.validate();
		
		AbstractMediaBean currentMediaBean2 = this.getCurrentMediaBean();
		
		if(currentMediaBean2.getName() == null) {
			lValidation.add(new ValidationResultBean(ValidationErrorType.ERROR, "name", "Das Feld Name muss ist ein Pflichtfeld."));
		}
		
		return lValidation;
	}


	private MediaVO getMediaVO() throws MPExeption {
		AbstractMediaBean lCurrentBean = this.getCurrentMediaBean();
		MediaVO lMediaVO = new MediaVO();
		
		lMediaVO.setId(lCurrentBean.getId());
		lMediaVO.setName(lCurrentBean.getName());
		
		return lMediaVO;
	}

	protected T getMediaBean() throws MPExeption {
		return PersistenceUtils.<T>toBean(this.currentMediaBean, this.currentMediaVO);
	}

	protected abstract void protectedSave() throws MPExeption;

	protected abstract void protectedDelete() throws MPExeption;

}