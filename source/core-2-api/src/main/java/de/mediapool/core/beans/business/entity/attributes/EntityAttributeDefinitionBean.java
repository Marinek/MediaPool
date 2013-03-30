package de.mediapool.core.beans.business.entity.attributes;

import de.mediapool.core.beans.AbstractBean;

public class EntityAttributeDefinitionBean extends AbstractBean {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private String entityType;

	private String attributeName;

	private String attributeDisplayName;

	private Integer attributeOrder;

	private Boolean attributeVisible;

	private Integer attributeSize;

	private AttributeType attributeType;

	private BeanAttributeMandatoryType mandatoryType;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public String getMediaType() {
		return entityType;
	}

	public void setEntityType(String mediaType) {
		this.entityType = mediaType;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public BeanAttributeMandatoryType getMandatoryType() {
		return mandatoryType;
	}

	public void setMandatoryType(BeanAttributeMandatoryType mandatoryType) {
		this.mandatoryType = mandatoryType;
	}

	public String getAttributeIdentifier() {
		return this.getMediaType() + "#" + this.getAttributeName();
	}

	public Integer getAttributeOrder() {
		return attributeOrder == null ? 1 : attributeOrder;
	}

	public void setAttributeOrder(Integer attributeOrder) {
		this.attributeOrder = attributeOrder;
	}

	public Boolean getAttributeVisible() {
		return attributeVisible;
	}

	public void setAttributeVisible(Boolean attributeVisible) {
		this.attributeVisible = attributeVisible;
	}

	public Integer getAttributeSize() {
		return attributeSize;
	}

	public void setAttributeSize(Integer attributeSize) {
		this.attributeSize = attributeSize;
	}

	public String getAttributeDisplayName() {
		return attributeDisplayName;
	}

	public void setAttributeDisplayName(String attributeField) {
		this.attributeDisplayName = attributeField;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
