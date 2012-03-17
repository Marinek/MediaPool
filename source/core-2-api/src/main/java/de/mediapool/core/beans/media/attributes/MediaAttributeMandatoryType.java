package de.mediapool.core.beans.media.attributes;

public enum MediaAttributeMandatoryType {
	MANDATORY("*", true),
	RECOMMENDED("+",false),
	NOTHING("",false);
	
	private final String infoString;
	private final boolean isMandatory;

	private MediaAttributeMandatoryType(String pInfoString, boolean isMandatory) {
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
