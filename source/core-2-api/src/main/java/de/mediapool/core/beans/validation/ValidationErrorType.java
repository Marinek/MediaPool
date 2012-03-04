package de.mediapool.core.beans.validation;

public enum ValidationErrorType {

	INFO (true),  WARN (true), ERROR(false);
	
	private boolean canProceed = true;
	
	private ValidationErrorType (boolean canProceed) {
		this.canProceed = canProceed;
	}

	public boolean canProceed() {
		return canProceed;
	}
}
