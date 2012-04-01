package de.mediapool.core.business.media.products;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.core.beans.PersistentStatus;
import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.media.products.MediaProductBean;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.media.products.MediaProductVO;
import de.mediapool.core.utils.ValidationUtil;

public class BOMediaProduct extends BusinessObject {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private AbstractMediaBean currentMediaBean;
	private List<MediaProductVO> currentProducts;

	private List<MediaProductBean> updateProducts = new ArrayList<MediaProductBean>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected BOMediaProduct(AbstractMediaBean pMediaBean, UserBean pUserBean) throws MPExeption {
		super(pUserBean);

		this.currentMediaBean = pMediaBean;

		this.init();
	}

	private void init() throws MPExeption {
		if(this.currentMediaBean == null) {
			throw new MPTechnicalExeption(ExeptionErrorCode.BO_INIT, "Es wurden nicht alle benötigten Daten übergeben: 'currentMediaBean'");
		}

		try {
			currentProducts = MediaProductVO.getDAO().getProductsByMediaId(this.currentMediaBean.getId());
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte auf Tabelle 'MediaProducts' nicht lesen.", e);
		}
	}

	public static BOMediaProduct getInstance(AbstractMediaBean pMediaBean, UserBean pUserBean) throws MPExeption {
		return new BOMediaProduct(pMediaBean, pUserBean);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public List<MediaProductBean> getProducts () throws MPExeption {
		List<MediaProductBean> lReturnList = new ArrayList<MediaProductBean>();

		for(MediaProductVO lProductVO : this.currentProducts) {
			lReturnList.add(this.getProductBean(lProductVO));
		}

		return lReturnList;
	}

	public List<ValidationResultBean> validate () throws MPExeption {
		List<ValidationResultBean> lResultValidation = super.validate();

		return lResultValidation;
	}

	public void save () throws MPExeption {
		if(ValidationUtil.canProceed(this.validate())) {
			try {
				for(MediaProductBean lProductBean : updateProducts) {
					MediaProductVO lVO = this.getMediaProductVO(lProductBean);

					MediaProductVO.getDAO().saveOrUpdate(lVO, this.getTransaction());
				}
				this.doCommit();
			} catch (PSException e) {
				this.doRollback();
				throw new MPTechnicalExeption(ExeptionErrorCode.DB_UPDATE, "Kann auf Tabelle 'MediaProducts' nicht schreiben.", e);
			}
		}
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private MediaProductVO getMediaProductVO(MediaProductBean lProductBean) {
		MediaProductVO lVO = new MediaProductVO();

		lVO.setProductid(lProductBean.getProductid());
		lVO.setProductean(lProductBean.getProductean());
		lVO.setProductname(lProductBean.getProductname());
		lVO.setMediaid(lProductBean.getMediaid());
		lVO.setProducttype(lProductBean.getProducttype());

		return lVO;
	}

	private MediaProductBean getProductBean(MediaProductVO lProductVO) {
		MediaProductBean lReturnMediaProductBean = new MediaProductBean();

		lReturnMediaProductBean.setMediaBean(this.currentMediaBean);
		lReturnMediaProductBean.setPersistentStatus(PersistentStatus.PERSISTENT);
		lReturnMediaProductBean.setProductean(lProductVO.getProductean());
		lReturnMediaProductBean.setProductid(lProductVO.getProductid());
		lReturnMediaProductBean.setProductname(lProductVO.getProductname());
		lReturnMediaProductBean.setProducttype(lProductVO.getProducttype());

		return lReturnMediaProductBean;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
