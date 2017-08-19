package com.github.bbijelic.torrent.core.torrents.magnet;

/**
 * Info hash provider exception
 * 
 * @author Bojan Bijelić
 */
public class MagnetLinkProviderException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    /**
	 * Constructor
	 * 
	 * @param message the exception message
	 */
	public MagnetLinkProviderException(String message){
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause the cause of exception
	 */
	public MagnetLinkProviderException(Throwable cause){
		super(cause.getMessage(), cause);
	}
    
}
