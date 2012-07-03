package de.mediapool.core.business.entities.products;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.entity.attributes.AttributedMediaBean;
import de.mediapool.core.beans.entity.products.MediaProductBean;
import de.mediapool.core.business.entities.BOAbstractEntity;
import de.mediapool.core.exceptions.MPExeption;

public class BOMediaProduct extends BOAbstractEntity<MediaProductBean> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected BOMediaProduct(MediaProductBean pMediaBean, UserBean pUserBean) throws MPExeption {
		super(pUserBean, pMediaBean);

	}

	protected BOMediaProduct(UUID pMediaBean, UserBean pUserBean) throws MPExeption {
		super(pMediaBean, pUserBean);
		
	}

	public static BOMediaProduct getInstance(MediaProductBean pMediaBean, UserBean pUserBean) throws MPExeption {
		return new BOMediaProduct(pMediaBean, pUserBean);
	}
	
	public static BOMediaProduct getInstance(UUID pMediaBean, UserBean pUserBean) throws MPExeption {
		return new BOMediaProduct(pMediaBean, pUserBean);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public List<AttributedMediaBean> getMedia () throws MPExeption {
		List<AttributedMediaBean> lReturnList = new ArrayList<AttributedMediaBean>();

		return lReturnList;
	}

//	public void save () throws MPExeption {
//		if(ValidationUtil.canProceed(this.validate())) {
//			try {
//				for(MediaProductBean lProductBean : updateProducts) {
//					MediaProductVO lVO = this.getMediaProductVO(lProductBean);
//
//					MediaProductVO.getDAO().saveOrUpdate(lVO, this.getTransaction());
//				}
//				this.doCommit();
//			} catch (PSException e) {
//				this.doRollback();
//				throw new MPTechnicalExeption(ExeptionErrorCode.DB_UPDATE, "Kann auf Tabelle 'MediaProducts' nicht schreiben.", e);
//			}
//		}
//	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected void protectedSave() throws MPExeption {
		
	}

	protected void protectedDelete() throws MPExeption {
		
	}

	protected MediaProductBean getCurrentEntityBeanInstance() throws MPExeption {
		return new MediaProductBean();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
