package com.github.bbijelic.torrent.core.torrents.infohash;

import java.util.Comparator;
import java.util.List;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.provider.Provider;


/**
 * Info hash provider for the episode.
 * 
 * @author Bojan Bijelić
 */
public interface InfoHashProvider extends Provider {
    
    /**
     * Finds best match info hash for a given episode
     * 
     * @param episode the episode information
     * @param comparators the list of comparators to filter best match for the info hash
     * @return the info hash string
     * @throws InfoHashProviderException
     */
    String getInfoHash(final Episode episode, final List<Comparator<ResultItem>> comparators) throws InfoHashProviderException;
    
}
