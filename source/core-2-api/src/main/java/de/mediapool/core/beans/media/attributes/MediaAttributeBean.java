package de.mediapool.core.beans.media.attributes;

import de.mediapool.core.beans.attributes.AttributeTypeBean;

public class MediaAttributeBean extends AttributeTypeBean {

	private String mediaType = null;
	
	private String attributeValue = null;

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

}
