package de.mediapool.core.business.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.PersistentStatus;
import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeBean;
import de.mediapool.core.beans.utils.PersistenceUtils;
import de.mediapool.core.beans.validation.ValidationErrorType;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.business.entities.attributes.EntityAttributeTypeManager;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityAttributeVO;
import de.mediapool.core.persistence.vo.entities.EntityVO;
import de.mediapool.core.utils.ValidationUtil;

public abstract class BOAbstractEntity<T extends AbstractEntityBean> extends BusinessObject {

	protected T currentEntity = null;

	protected EntityVO currentEntityVO = null;

	private List<EntityAttributeVO> currentAttributes = new ArrayList<EntityAttributeVO>();

	protected BOAbstractEntity (UserBean pUserBean) throws MPExeption {
		super(pUserBean);
	}

	protected BOAbstractEntity (UUID entityID, UserBean pUserBean) throws MPExeption {
		this(pUserBean);

		this.init(entityID);
	}

	protected BOAbstractEntity(UserBean pUserBean, T pMediaBean) throws MPExeption {
		this(pMediaBean.getId(), pUserBean);
	}

	protected void init(UUID mediaID) throws MPExeption {
		try {
			
			if(mediaID == null) {
				throw new MPBusinessExeption(ExeptionErrorCode.BO_INIT, "Es wurde keine ID für dieses Businessobjekt angegeben!");
			}
			currentEntityVO = EntityVO.getDAO().getByPrimaryKey(mediaID.toString());

			currentAttributes = EntityAttributeVO.getDAO().getAttributesFor(this.currentEntityVO.getId());
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Could not Read entitys", e);
		}
	}

	public void setCurrentEntityBean(T abstractMediaBean) throws MPExeption {
		this.currentEntity = abstractMediaBean;
	}

	public T getCurrentEntityBean () throws MPExeption {
		if(this.currentEntity == null) {
			this.currentEntity = this.getCurrentEntityBeanInstance(); 
		}

		if(this.currentAttributes != null) {
			for(EntityAttributeVO lVO : this.currentAttributes) {
				EntityAttributeBean lAttributeBean = new EntityAttributeBean();

				EntityAttributeTypeManager.getInstance().getAttribute(lVO.getAttributeName(), this.currentEntity.getEntityType());

				lAttributeBean.setAttributeValue(lVO.getAttributeValue());
				lAttributeBean.setId(lVO.getId());

				currentEntity.addAttribute(lAttributeBean);
			}
		}

		return this.currentEntity;
	}

	public void delete()  throws MPExeption {
		EntityVO deleteMovie = this.getEntityVO();

		try {
			EntityVO.getDAO().delete(deleteMovie, this.getTransaction());
			this.protectedDelete();

			this.doCommit();
		} catch (PSException e) {
			this.doRollback();
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_DELETE, "Could not Delete EntityVO", e);
		}
	}


	public List<ValidationResultBean> save() throws MPExeption {
		List<ValidationResultBean> validationResult = this.validate();

		if(ValidationUtil.canProceed(validationResult)) {
			this.currentEntityVO = this.getEntityVO();

			try {
				EntityVO.getDAO().saveOrUpdate(currentEntityVO, this.getTransaction());

				this.currentAttributes.clear();

				for(EntityAttributeBean lAttribute : this.getCurrentEntityBean().getAttributes()) {
					EntityAttributeVO lVO = new EntityAttributeVO();

					lVO.setAttributeName(lAttribute.getIdAsString());
					lVO.setAttributeName(lAttribute.getAttributeName());
					lVO.setMediaID(currentEntityVO.getId());
					lVO.setAttributeValue(lAttribute.getAttributeValue());

					EntityAttributeVO.getDAO().saveOrUpdate(lVO, this.getTransaction());

					this.currentAttributes.add(lVO);
				}

				this.protectedSave();

				this.doCommit();

				this.currentEntity.setPersistentStatus(PersistentStatus.PERSISTENT);
			} catch (PSException e) {
				this.doRollback();

				throw new MPTechnicalExeption(ExeptionErrorCode.DB_UPDATE, "Could not Update MediaVO", e);
			}
		}
		return validationResult;
	}

	public List<ValidationResultBean> validate() throws MPExeption {
		List<ValidationResultBean> lValidation = super.validate();

		AbstractEntityBean currentMediaBean2 = this.getCurrentEntityBean();

		if(currentMediaBean2.getName() == null) {
			lValidation.add(new ValidationResultBean(ValidationErrorType.ERROR, "name", "Das Feld Name muss ist ein Pflichtfeld."));
		}

		return lValidation;
	}


	private EntityVO getEntityVO() throws MPExeption {
		AbstractEntityBean lCurrentBean = this.getCurrentEntityBean();
		EntityVO lMediaVO = new EntityVO();

		lMediaVO.setId(lCurrentBean.getIdAsString());
		lMediaVO.setName(lCurrentBean.getName());

		return lMediaVO;
	}

	protected T getMediaBean() throws MPExeption {
		return PersistenceUtils.<T>toBean(this.currentEntity, this.currentEntityVO);
	}

	protected abstract void protectedSave() throws MPExeption;

	protected abstract void protectedDelete() throws MPExeption;

	protected abstract T getCurrentEntityBeanInstance() throws MPExeption;

}