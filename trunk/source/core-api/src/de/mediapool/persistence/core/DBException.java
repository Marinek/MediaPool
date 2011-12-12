package de.mediapool.persistence.core;

public class DBException extends Exception {

	private static final long serialVersionUID = 1L;

	public DBException(String pMessage) {
		super(pMessage);
	}
}
