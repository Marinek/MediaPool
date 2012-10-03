package de.mediapool.core.business.authentication;

import java.util.UUID;

import org.apache.shiro.subject.Subject;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPExeption;

public class BOUserAuthentication extends BusinessObject {

	public BOUserAuthentication() throws MPExeption {
		super(null);
	}

	public UserBean login(String pUsername, String pPassword) throws MPExeption {
		// SecurityUtils.setSecurityManager(new DefaultSecurityManager());
		// Subject currentUser = SecurityUtils.getSubject();
		UserBean lUserBean = new UserBean();

		try {
			// UniqueUsernameAndPasswortAuth lToken = new
			// UniqueUsernameAndPasswortAuth(pUsername, pPassword);

			// currentUser.login(lToken);

			// Object principal = currentUser.getPrincipal();

			// if(principal != null) {
			// AuthenticationCache.getInstance().addSubject(currentUser);

			lUserBean.setId(UUID.randomUUID());
			lUserBean.setAuthed(true);
			lUserBean.setAccountName(pUsername);
			lUserBean.setDisplayName(lUserBean.getIdAsString() + "-" + lUserBean.getAccountName());
			// }
		} catch (Exception e) {
			throw new MPBusinessExeption(ExeptionErrorCode.AUTH_LOGIN_CACHE, "Fehler beim Authentifizieren", e);
		}
		return lUserBean;
	}

	public boolean isAuthenticated(UserBean pUserBean) throws MPExeption {
		return getUserBean(pUserBean).isAuthenticated();
	}

	public void logout(UserBean pUserBean) throws MPExeption {
		this.getUserBean(pUserBean).logout();
	}

	private Subject getUserBean(UserBean pUserBean) throws MPExeption {
		return AuthenticationCache.getInstance().getSubject(pUserBean.getId());
	}
}
