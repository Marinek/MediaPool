package de.mediapool.core.business.authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.exceptions.MPException;

public class AuthenticationCache {

	private static AuthenticationCache instance;

	private Map<UUID, UserBean> currentKnownUser = new HashMap<UUID, UserBean>();

	public static final AuthenticationCache getInstance() {
		if (instance == null) {
			instance = new AuthenticationCache();
		}

		return instance;
	}

	public void addSubject(UserBean pSubject) throws MPException {
		this.currentKnownUser.put(pSubject.getSessionId(), pSubject);
	}

	public UserBean getSubject(UUID pUUID) throws MPException {
		return this.currentKnownUser.get(pUUID);
	}

	public void removeSubject(UserBean pSubject) throws MPException {
		this.currentKnownUser.remove(pSubject.getSessionId());
	}

}
