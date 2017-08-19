package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay;

import java.util.List;

import com.github.bbijelic.torrent.core.episodes.Episode;

/**
 * Search interface
 */
public interface SearchInterface {
    
    /**
     * Performs the search and returns resulting list
     * 
     * @param episode the episode information
     * @return the resulting list
     */
    List<PirateBaySearchResultItem> search(Episode episode);
    
}
