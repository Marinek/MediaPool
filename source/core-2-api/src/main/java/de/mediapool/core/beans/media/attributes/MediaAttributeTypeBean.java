package de.mediapool.core.beans.media.attributes;

import de.mediapool.core.beans.AbstractBean;

public class MediaAttributeTypeBean extends AbstractBean {

	private String attributeMediaType = null;
	private String attributeName = null;
	private String attributeDisplay = null;
	
	public String getAttributeMediaType() {
		return attributeMediaType;
	}
	public void setAttributeMediaType(String attributeMediaType) {
		this.attributeMediaType = attributeMediaType;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeDisplay() {
		return attributeDisplay;
	}
	public void setAttributeDisplay(String attributeDisplay) {
		this.attributeDisplay = attributeDisplay;
	}
	
}
