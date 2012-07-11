package de.mediapool.core.beans.business.authentication;

import de.mediapool.core.beans.AbstractBean;

public class UserBean extends AbstractBean {
	
	private static final long serialVersionUID = 1L;

	private String displayName = null;
	
	private boolean isAuthed = false;
	
	private String accountName = "";
	
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

}
