package de.mediapool.core.beans.business.entity.attributes;

import java.util.Date;

public enum AttributeType {

	STRING(String.class, "com.vaadin.ui.TextField"), DATE(Date.class, "com.vaadin.ui.DateField");

	private final Class<?> storeClassName;
	private final String viewClassName;

	private AttributeType(Class<?> pStoreClassName, String pViewClassName) {
		storeClassName = pStoreClassName;
		viewClassName = pViewClassName;
	}

	public Class<?> getStoreClass() {
		return storeClassName;
	}

	public String getViewClassName() {
		return viewClassName;
	}

}
