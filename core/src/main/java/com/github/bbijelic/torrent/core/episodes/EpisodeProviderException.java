package com.github.bbijelic.torrent.core.episodes;

/**
 * Episode provider exception
 *
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public class EpisodeProviderException extends Exception {

	private static final long serialVersionUID = 6178549893388626504L;

	/**
	 * Constructor
	 * 
	 * @param message the exception message
	 */
	public EpisodeProviderException(String message){
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause the cause of exception
	 */
	public EpisodeProviderException(EpisodeProviderException cause){
		super(cause.getMessage(), cause);
	}
	
}
