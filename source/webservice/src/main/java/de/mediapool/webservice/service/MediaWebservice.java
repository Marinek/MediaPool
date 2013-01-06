package de.mediapool.webservice.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IAuthService;

/**
 * @author Marcinek
 */
@WebService
@SOAPBinding(style = Style.RPC)
public class MediaWebservice {

	@Resource
	private IAuthService authService = null;

	@WebMethod
	public UserBean authentication(String pUsername, String pPassword) throws MPExeption {
		return authService.auth(pUsername, pPassword);
	}

	public IAuthService getAuthService() {
		return authService;
	}

	public void setAuthService(IAuthService authService) {
		this.authService = authService;
	}
}
