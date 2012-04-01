package de.mediapool.core.beans;


public abstract class AbstractBean {
	
	private PersistentStatus persistentStatus = PersistentStatus.NOTPERSISTENT;
	
	public boolean isPersistent () {
		return this.getPersistentStatus() == PersistentStatus.PERSISTENT;
	}

	public void setPersistentStatus(PersistentStatus persistentStatus) {
		this.persistentStatus = persistentStatus;
	}

	public PersistentStatus getPersistentStatus() {
		return persistentStatus;
	}


}
