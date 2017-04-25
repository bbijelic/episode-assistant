package com.github.bbijelic.torrent.core.provider;

/**
 * Provider information interface
 * 
 * @author Bojan Bijelić
 */
public interface Provider {
    
    /**
     * Provider name getter
     * 
     * @return the provider name
     */
    String getName();
    
    
    /**
     * Provider description getter
     * 
     * @return the provider description
     */
    String getDescription();
    
    /**
     * Provider author getter
     * 
     * @return the provider author
     */
    String getAuthor();
    
}
