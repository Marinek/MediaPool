package de.mediapool.core.beans.business.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mediapool.core.beans.business.entity.attributes.AttributeValueBean;
import de.mediapool.core.utils.AttributeUtil;

public abstract class AbstractSingleEntityBean extends AbstractEntityBean {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private String name;
	private String entityType;

	private Map<String, AttributeValueBean<?>> attributes = new HashMap<String, AttributeValueBean<?>>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public AbstractSingleEntityBean() {
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String mediaType) {
		this.entityType = mediaType;
	}

	public AttributeValueBean<?> getAttribute(String pAttributeName) {
		AttributeValueBean<?> lReturnString = null;

		if (attributes.containsKey(pAttributeName)) {
			lReturnString = attributes.get(pAttributeName);
		}

		return lReturnString;
	}

	public void setAttribute(String pAttributeName, String pValue) {
		if (!attributes.containsKey(this.getAttributeIdentifier(pAttributeName))) {
			throw new IllegalArgumentException("Attribute '" + pAttributeName + "' existiert nicht.");
		}
		this.attributes.get(this.getAttributeIdentifier(pAttributeName)).setAttributeValue(pValue);
	}

	public void addAttribute(AttributeValueBean<?> pAttributeBean) {
		attributes.put(pAttributeBean.getAttributeIdentifier(), pAttributeBean);
	}

	public Collection<AttributeValueBean<?>> getAttributes() {
		List<AttributeValueBean<?>> lReturnList = new ArrayList<AttributeValueBean<?>>();

		for (String lKey : this.attributes.keySet()) {
			lReturnList.add(this.attributes.get(lKey));
		}

		AttributeUtil.sort(lReturnList);

		return Collections.unmodifiableCollection(lReturnList);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public String toString() {
		return "AbstractSingleEntityBean [name=" + name + ", entityType=" + entityType + ", attributes=" + attributes + "]";
	}

	private String getAttributeIdentifier(String pAttributeName) {
		return this.getEntityType() + "#" + pAttributeName;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
