package de.mediapool.core.exceptions;


public class MPBusinessExeption extends MPExeption {

	private static final long serialVersionUID = 1L;

	public MPBusinessExeption(ExeptionErrorCode errorCode, String detailMessage, Throwable throwable) {
		super(errorCode, detailMessage, throwable);
	}
	
	public MPBusinessExeption(ExeptionErrorCode errorCode, String detailMessage) {
		super(errorCode, detailMessage);
	}
}
