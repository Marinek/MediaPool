package de.mediapool.core.beans.business.entity.attributes;

public enum BeanAttributeMandatoryType {
	MANDATORY("*", true),
	RECOMMENDED("+",false),
	NOTHING("",false);
	
	private final String infoString;
	private final boolean isMandatory;

	private BeanAttributeMandatoryType(String pInfoString, boolean isMandatory) {
		this.infoString = pInfoString;
		this.isMandatory = isMandatory;
		
	}

	public String getInfoString() {
		return infoString;
	}

	public boolean isMandatory() {
		return isMandatory;
	}
}
