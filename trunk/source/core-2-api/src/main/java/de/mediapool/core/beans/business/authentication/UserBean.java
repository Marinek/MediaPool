package de.mediapool.core.beans.business.authentication;

import java.util.UUID;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.utils.HashUtil;

public class UserBean extends AbstractBean {

	private static final long serialVersionUID = 2L;

	private UUID sessionId = null;

	private String displayName = null;

	private String accountName = "";

	private String passwort = null;

	private boolean isAuthed = false;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isAuthed() {
		return isAuthed;
	}

	public void setAuthed(boolean isAuthed) {
		this.isAuthed = isAuthed;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public UUID getSessionId() {
		return sessionId;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = HashUtil.md5Hash(passwort);
	}

}
