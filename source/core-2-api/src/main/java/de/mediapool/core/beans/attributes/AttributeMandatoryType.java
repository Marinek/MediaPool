package de.mediapool.core.beans.attributes;

public enum AttributeMandatoryType {
	MANDATORY("*", true),
	RECOMMENDED("+",false),
	NOTHING("",false);
	
	private final String infoString;
	private final boolean isMandatory;

	private AttributeMandatoryType(String pInfoString, boolean isMandatory) {
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
