package de.mediapool.core.beans.authentication;

import java.util.UUID;

import de.mediapool.core.beans.AbstractBean;

public class UserBean extends AbstractBean {

	private UUID uuid = null;
	
	private String displayName = null;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
