package de.mediapool.core.business.search.profiles;

import java.util.List;

import com.thoughtworks.xstream.XStream;

import de.mediapool.core.beans.PersistentStatus;
import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.search.entity.AbstractEntitySearchBean;
import de.mediapool.core.beans.search.profiles.SearchProfileBean;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.searchprofiles.SearchProfile;
import de.mediapool.core.utils.NullValidationUtil;
import de.mediapool.core.utils.ValidationUtil;

public class BOEntitySearchProfile extends BusinessObject {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private SearchProfileBean currentSearchProfileBean;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public BOEntitySearchProfile(UserBean pUserBean) throws MPExeption {
		super(pUserBean);

	}

	public void save() throws MPExeption {
		NullValidationUtil.checkForNull(this.currentSearchProfileBean, "currentSearchProfileBean");

		List<ValidationResultBean> validate = this.validate();

		if (!ValidationUtil.canProceed(validate)) {
			throw new MPBusinessExeption(ExeptionErrorCode.BO_VALIDATION, ValidationUtil.toString(validate));
		}

		try {
			SearchProfile lSearchProfile = this.getVO(this.currentSearchProfileBean);

			SearchProfile.getDAO().saveOrUpdate(lSearchProfile, this.getTransaction());

			this.currentSearchProfileBean.setPersistentStatus(PersistentStatus.PERSISTENT);

			this.doCommit();
		} catch (PSException e) {
			this.doRollback();
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte SearchProfile nicht schreiben.");
		}
	}

	public BOEntitySearchProfile(UserBean pUserBean, SearchProfileBean pAbstractEntitySearchBean) throws MPExeption {
		this(pUserBean);

		NullValidationUtil.checkForNull(pAbstractEntitySearchBean, "pAbstractEntitySearchBean");

		currentSearchProfileBean = pAbstractEntitySearchBean;
	}

	public BOEntitySearchProfile(UserBean pUserBean, String pProfileId) throws MPExeption {
		this(pUserBean);

		NullValidationUtil.checkForNull(pProfileId, "pProfileId");

		this.init(pProfileId);
	}

	public SearchProfileBean getSearchProfileBean() {
		return this.currentSearchProfileBean;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public SearchProfileBean getSearchBean() throws MPExeption {
		return this.currentSearchProfileBean;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private void init(String pProfileId) throws MPExeption {
		if (this.currentSearchProfileBean == null) {
			try {
				SearchProfile searchProfileVO = SearchProfile.getDAO().findById(pProfileId);

				this.currentSearchProfileBean = this.getBean(searchProfileVO);
			} catch (PSException e) {
				throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte auf Tabelle 'searchprofiles' nicht lesen.");
			}
		}
	}

	private SearchProfileBean getBean(SearchProfile searchProfileVO) {
		SearchProfileBean lSearchProfileBean = new SearchProfileBean();

		lSearchProfileBean.setId(searchProfileVO.getId());
		lSearchProfileBean.setPersistentStatus(PersistentStatus.PERSISTENT);
		lSearchProfileBean.setName(searchProfileVO.getName());

		XStream xstream = new XStream();

		AbstractEntitySearchBean lAbstractEntitySearchBean = (AbstractEntitySearchBean) xstream.fromXML(new String(searchProfileVO.getSearchDefinition()));

		lSearchProfileBean.setSearchBean(lAbstractEntitySearchBean);

		return lSearchProfileBean;
	}

	private SearchProfile getVO(SearchProfileBean searchProfileBean) {
		SearchProfile lVO = new SearchProfile();

		lVO.setId(searchProfileBean.getIdAsString());
		lVO.setName(searchProfileBean.getName());

		XStream xstream = new XStream();

		String lSearchDefinition = xstream.toXML(searchProfileBean.getSearchBean());

		lVO.setSearchDefinition(lSearchDefinition.getBytes());

		return lVO;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

}
