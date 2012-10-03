package de.mediapool.core.beans.business.entity.attributes;

public class EntityAttributeValueBean extends EntityAttributeDefinitionBean {

	private static final long serialVersionUID = 1L;

	private String attributeDisplay;
	private String attributeValue = null;

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeDisplay() {
		return attributeDisplay;
	}

	public void setAttributeDisplay(String attributeDisplay) {
		this.attributeDisplay = attributeDisplay;
	}

}
