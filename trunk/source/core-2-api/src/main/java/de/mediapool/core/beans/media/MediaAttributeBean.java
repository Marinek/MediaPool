package de.mediapool.core.beans.media;

import de.mediapool.core.beans.media.attributes.MediaAttributeTypeBean;

public class MediaAttributeBean {

	private MediaAttributeTypeBean attributeType = new MediaAttributeTypeBean();
	
	private String attributeValue = null;

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public MediaAttributeTypeBean getAttributeType() {
		return this.attributeType;
	}
	
	public void setAttributeType(MediaAttributeTypeBean attributeType) {
		this.attributeType = attributeType;
	}
}
