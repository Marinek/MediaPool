package de.mediapool.core.services.interfaces;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.exceptions.MPExeption;

public interface IAuthService extends IService {

	public UserBean auth (String pUserName, String pPasswort) throws MPExeption;
}
