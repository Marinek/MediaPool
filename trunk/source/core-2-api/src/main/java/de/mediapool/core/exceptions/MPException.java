package de.mediapool.core.exceptions;

import java.rmi.RemoteException;

public abstract class MPException extends RemoteException {

	private static final long serialVersionUID = 1L;

	private ExeptionErrorCode errorCode;

	public MPException() {

	}

	public MPException(ExeptionErrorCode errorCode, String detailMessage) {
		this(errorCode, detailMessage, null);
	}

	public MPException(ExeptionErrorCode errorCode, String detailMessage, Throwable throwable) {
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
