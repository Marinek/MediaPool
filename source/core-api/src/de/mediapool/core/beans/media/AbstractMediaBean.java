package de.mediapool.core.beans.media;

import de.mediapool.core.beans.AbstractBean;

public abstract class AbstractMediaBean extends AbstractBean {

	private int id;
	private String name;
	
	private MediaType mediaType = null;
	
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

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

}
