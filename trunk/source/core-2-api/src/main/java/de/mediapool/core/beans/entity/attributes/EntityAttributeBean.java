package de.mediapool.core.beans.entity.attributes;

import de.mediapool.core.beans.attributes.AttributeBean;

public class EntityAttributeBean extends AttributeBean {

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

	public void setEntityType(String mediaType) {
		this.mediaType = mediaType;
	}

}
