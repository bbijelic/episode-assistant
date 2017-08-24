package com.github.bbijelic.torrent.core.torrents.magnet;

/**
 * Search provider exception
 * 
 * @author Bojan Bijelic
 *
 */
public class SearchProviderException extends Exception {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -8001916033901789771L;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public SearchProviderException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message
	 */
	public SearchProviderException(final String message) {
		super(message);
	}

}
