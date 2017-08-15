package com.github.bbijelic.torrent.db.core;

/**
 * JPA exception
 *
 * @author Bojan Bijelic
 */
public class JpaException extends Exception {

	private static final long serialVersionUID = -5868061882689703519L;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message
	 */
	public JpaException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public JpaException(String message, Throwable cause) {
		super(message, cause);
	}

}
