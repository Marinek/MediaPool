package de.mediapool.core.business.search.profiles;

import java.util.ArrayList;
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
import de.mediapool.core.exceptions.MPException;
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

	public BOEntitySearchProfile(UserBean pUserBean) throws MPException {
		super(pUserBean);

	}

	public void save() throws MPException {
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

	public BOEntitySearchProfile(UserBean pUserBean, SearchProfileBean pAbstractEntitySearchBean) throws MPException {
		this(pUserBean);

		NullValidationUtil.checkForNull(pAbstractEntitySearchBean, "pAbstractEntitySearchBean");

		currentSearchProfileBean = pAbstractEntitySearchBean;
	}

	public BOEntitySearchProfile(UserBean pUserBean, String pProfileId) throws MPException {
		this(pUserBean);

		NullValidationUtil.checkForNull(pProfileId, "pProfileId");

		this.init(pProfileId);
	}

	public List<SearchProfileBean> getSearchProfiles() throws MPException {
		List<SearchProfileBean> lReturnList = new ArrayList<SearchProfileBean>();

		try {
			List<SearchProfile> userProfileVOs = SearchProfile.getDAO().findByUser(this.getCurrentUserBean().getIdAsString());

			for (SearchProfile lProfileVO : userProfileVOs) {
				lReturnList.add(this.getBean(lProfileVO));
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte auf Tabelle 'SuchProfil' nicht lesen.", e);
		}

		return lReturnList;
	}

	public SearchProfileBean getSearchProfileBean() throws MPException {
		return this.currentSearchProfileBean;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public SearchProfileBean getSearchBean() throws MPException {
		return this.currentSearchProfileBean;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private void init(String pProfileId) throws MPException {
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
		lVO.setOwnerId(this.getCurrentUserBean().getIdAsString());

		XStream xstream = new XStream();

		String lSearchDefinition = xstream.toXML(searchProfileBean.getSearchBean());

		lVO.setSearchDefinition(lSearchDefinition.getBytes());

		return lVO;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

}
