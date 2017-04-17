package com.github.bbijelic.torrent.core.torrents.infohash;

import com.github.bbijelic.torrent.core.episodes.Episode;


/**
 * Info hash provider for the episode.
 * 
 * @author Bojan Bijelić
 */
public interface InfoHashProvider {
    
    /**
     * Finds best match info hash for a given episode
     * 
     * @param episode the episode information
     * @return the info hash string
     * @throws InfoHashProviderException
     */
    String getInfoHash(Episode episode) throws InfoHashProviderException;
    
}
