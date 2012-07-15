package de.mediapool.core.beans;

import java.util.UUID;

import de.mediapool.core.beans.interfaces.IBean;


public abstract class AbstractBean implements IBean {
	
	private static final long serialVersionUID = 1L;

	private PersistentStatus persistentStatus = PersistentStatus.NOTPERSISTENT;
	
	private UUID beanId = UUID.randomUUID();
	
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
	
	public String getIdAsString() {
		return this.beanId != null ? this.beanId.toString() : null;
	}

	public void setId(UUID beanId) {
		this.beanId = beanId;
	}

	public void setId(String beanId) {
		this.beanId = UUID.fromString(beanId);
	}


}
