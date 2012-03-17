package de.mediapool.core.beans.media;

import de.mediapool.core.beans.AbstractBean;

public abstract class AbstractMediaBean extends AbstractBean {

	private Integer id;
	private String name;
	private String mediaType;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}


}
