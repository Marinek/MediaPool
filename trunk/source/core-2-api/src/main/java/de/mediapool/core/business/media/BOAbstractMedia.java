package de.mediapool.core.business.media;

import java.util.List;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.utils.PersistenceUtils;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.DBException;
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
		} catch (DBException e) {
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
			MediaVO.getDAO().delete(deleteMovie);
		} catch (DBException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_DELETE, "Could not Delete MediaVO", e);
		}

		this.protectedDelete();
	}


	public List<ValidationResultBean> save() throws MPExeption {
		List<ValidationResultBean> validationResult = this.validate();

		if(ValidationUtil.canProceed(validationResult)) {
			MediaVO saveMovieVO = this.getMediaVO();

			try {
				this.currentMediaVO = MediaVO.getDAO().insert(saveMovieVO);
				this.protectedSave();
			} catch (DBException e) {
				throw new MPTechnicalExeption(ExeptionErrorCode.DB_UPDATE, "Could not Update MediaVO", e);
			}
		}
		return validationResult;
	}


	private MediaVO getMediaVO() throws MPExeption {
		return PersistenceUtils.<MediaVO>toVO(MediaVO.class, this.getCurrentMediaBean());
	}

	protected T getMediaBean() throws MPExeption {
		return PersistenceUtils.<T>toBean(this.currentMediaBean, this.currentMediaVO);
	}

	protected abstract void protectedSave() throws MPExeption;

	protected abstract void protectedDelete() throws MPExeption;

}