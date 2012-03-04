package de.mediapool.core.beans.media;

import de.mediapool.core.beans.AbstractBean;

public abstract class AbstractMediaBean extends AbstractBean {

	private int id;
	private String name;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract MediaType getMediaType();

}
