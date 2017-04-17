package com.github.bbijelic.torrent.core.torrents.infohash;

/**
 * Info hash provider exception
 * 
 * @author Bojan Bijelić
 */
public class InfoHashProviderException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    /**
	 * Constructor
	 * 
	 * @param message the exception message
	 */
	public InfoHashProviderException(String message){
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause the cause of exception
	 */
	public InfoHashProviderException(Throwable cause){
		super(cause.getMessage(), cause);
	}
    
}
