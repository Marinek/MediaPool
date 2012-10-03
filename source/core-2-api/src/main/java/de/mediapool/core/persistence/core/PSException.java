package de.mediapool.core.persistence.core;

public class PSException extends Exception {

	private static final long serialVersionUID = 1L;

	public PSException(String pMessage) {
		super(pMessage);
	}

	public PSException(String pMessage, Throwable couse) {
		super(pMessage, couse);
	}

}
