package de.mediapool.core.beans.business.entity.attributes;

import de.mediapool.core.beans.AbstractBean;


public class EntityAttributeBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private String entityType = null;
	
	private String attributeType;
	private String attributeName;
	private String attributeDisplay;
	private BeanAttributeMandatoryType mandatoryType =  BeanAttributeMandatoryType.NOTHING;
	
	private String attributeValue = null;

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getMediaType() {
		return entityType;
	}

	public void setEntityType(String mediaType) {
		this.entityType = mediaType;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
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

	public BeanAttributeMandatoryType getMandatoryType() {
		return mandatoryType;
	}

	public void setMandatoryType(BeanAttributeMandatoryType mandatoryType) {
		this.mandatoryType = mandatoryType;
	}

}
