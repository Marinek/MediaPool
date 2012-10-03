package de.mediapool.core.beans.validation;

import de.mediapool.core.beans.AbstractBean;

public class ValidationResultBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private String fieldName;

	private String message;

	private ValidationErrorType validationErrorType;

	public ValidationResultBean() {

	}

	public ValidationResultBean(ValidationErrorType errorType, String fieldname, String message) {
		this.setValidationErrorType(errorType);
		this.setFieldName(fieldname);
		this.setMessage(message);
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setValidationErrorType(ValidationErrorType validationErrorType) {
		this.validationErrorType = validationErrorType;
	}

	public ValidationErrorType getValidationErrorType() {
		return validationErrorType;
	}

	public boolean isPersistent() {
		return false;
	}

	public String toString() {
		return validationErrorType + ": '" + fieldName + "' -> " + message;
	}
}
