package com.github.bbijelic.torrent.core.torrents.file;

/**
 * Torrent file provider exception
 * 
 * @author Bojan Bijelić
 */
public class TorrentFileProviderException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    /**
	 * Constructor
	 * 
	 * @param message the exception message
	 */
	public TorrentFileProviderException(String message){
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause the cause of exception
	 */
	public TorrentFileProviderException(Throwable cause){
		super(cause.getMessage(), cause);
	}
    
}
