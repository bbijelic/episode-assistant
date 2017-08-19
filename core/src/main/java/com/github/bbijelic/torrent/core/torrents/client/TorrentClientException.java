package com.github.bbijelic.torrent.core.torrents.client;

/**
 * Torrent client exception
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentClientException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the exception message
	 */
	public TorrentClientException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            the cause of exception
	 */
	public TorrentClientException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

}
