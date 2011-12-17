package de.mediapool.exceptions;

public abstract class MPExeption extends Exception {

	private static final long serialVersionUID = 1L;

	private ExeptionErrorCode errorCode;
	
	public MPExeption (ExeptionErrorCode errorCode, String detailMessage) {
		this(errorCode, detailMessage, null);
	}
	
	public MPExeption(ExeptionErrorCode errorCode, String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		
		this.errorCode = errorCode; 
	}

	public ExeptionErrorCode getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return "[" + this.errorCode.getErrorCode() + "] - " + this.errorCode.getMessage() + " - " + super.getMessage();
	}
	
	
}
