package de.mediapool.core.beans;

import java.util.UUID;


public abstract class AbstractBean {
	
	private PersistentStatus persistentStatus = PersistentStatus.NOTPERSISTENT;
	
	private UUID beanId = null;
	
	public boolean isPersistent () {
		return this.getPersistentStatus() == PersistentStatus.PERSISTENT;
	}

	public void setPersistentStatus(PersistentStatus persistentStatus) {
		this.persistentStatus = persistentStatus;
	}

	public PersistentStatus getPersistentStatus() {
		return persistentStatus;
	}

	public UUID getId() {
		return beanId;
	}

	public void setId(UUID beanId) {
		this.beanId = beanId;
	}

	public void setId(String beanId) {
		this.beanId = UUID.fromString(beanId);
	}


}
