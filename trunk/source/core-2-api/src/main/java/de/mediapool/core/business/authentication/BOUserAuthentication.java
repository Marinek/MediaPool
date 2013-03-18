package de.mediapool.core.business.authentication;

import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.user.UserVO;

public class BOUserAuthentication extends BusinessObject {

	public BOUserAuthentication() throws MPException {
		super(null);
	}

	public UserBean login(String pUsername, String pPassword) throws MPException {
		UserBean lUserBean = new UserBean();

		try {
			List<UserVO> lUserVOList = UserVO.getDAO().findBy(pUsername, pPassword);

			if (lUserVOList.size() != 1) {
				throw new MPBusinessExeption(ExeptionErrorCode.AUTH_LOGIN, "Fehler beim Authentifizieren");
			} else {
				lUserBean = this.getUserBean(lUserVOList.get(0));

				lUserBean.setSessionId(UUID.randomUUID());

				AuthenticationCache.getInstance().addSubject(lUserBean);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Fehler beim Lesen auf Tabelle 'User'", e);
		}

		return lUserBean;
	}

	private UserBean getUserBean(UserVO userVO) {
		UserBean lUserBean = new UserBean();

		lUserBean.setId(userVO.getId());
		lUserBean.setAccountName(userVO.getUsername() + " (" + userVO.getEmail() + ")");

		return lUserBean;
	}

	public boolean isAuthenticated(UserBean pUserBean) throws MPException {
		if (pUserBean == null) {
			throw new MPTechnicalExeption(ExeptionErrorCode.AUTH_LOGIN, "Unbekannter Nutzer (NULL)");
		}

		return AuthenticationCache.getInstance().getSubject(pUserBean.getSessionId()) != null;
	}

	public void logout(UserBean pUserBean) throws MPException {
		AuthenticationCache.getInstance().removeSubject(pUserBean);
	}
}
