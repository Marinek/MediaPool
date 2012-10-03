package de.mediapool.core.business.authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.subject.Subject;

import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPExeption;

public class AuthenticationCache {

	private static AuthenticationCache instance;

	private Map<UUID, Subject> currentKnownUser = new HashMap<UUID, Subject>();

	public static final AuthenticationCache getInstance() {
		if (instance == null) {
			instance = new AuthenticationCache();
		}

		return instance;
	}

	public void addSubject(Subject pSubject) throws MPExeption {
		if (pSubject.getPrincipal() instanceof UUID) {
			this.currentKnownUser.put((UUID) pSubject.getPrincipal(), pSubject);
		} else {
			throw new MPBusinessExeption(ExeptionErrorCode.AUTH_LOGIN, "Konnte Benutzer nicht im Cache Speichern!");
		}
	}

	public Subject getSubject(UUID pUUID) throws MPExeption {
		return this.currentKnownUser.get(pUUID);
	}

}
