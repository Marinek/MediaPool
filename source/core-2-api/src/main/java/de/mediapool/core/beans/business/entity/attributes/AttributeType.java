package de.mediapool.core.beans.business.entity.attributes;

public enum AttributeType {

	STRING("java.lang.String");

	private final String storeClassName;

	private AttributeType(String pStoreClassName) {
		storeClassName = pStoreClassName;

	}

	public String getStoreClassName() {
		return storeClassName;
	}

}
