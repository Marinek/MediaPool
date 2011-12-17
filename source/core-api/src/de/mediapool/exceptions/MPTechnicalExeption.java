package de.mediapool.exceptions;


public class MPTechnicalExeption extends MPExeption {

	private static final long serialVersionUID = 1L;

	public MPTechnicalExeption(ExeptionErrorCode errorCode, String detailMessage) {
		super(errorCode, detailMessage);
	}
	
	public MPTechnicalExeption(ExeptionErrorCode errorCode, String detailMessage, Throwable throwable) {
		super(errorCode, detailMessage, throwable);
	}
}
