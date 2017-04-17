package com.github.bbijelic.torrent.providers.torrents.infohash.piratebay;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.torrents.infohash.InfoHashProvider;
import com.github.bbijelic.torrent.core.torrents.infohash.InfoHashProviderException;

/**
 * PirateBay.org info hash provider implementation
 * 
 * @author Bojan Bijelić
 */
public class PirateBayInfoHashProvider implements InfoHashProvider {
        
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PirateBayInfoHashProvider.class);
    
    @Override
    public String getInfoHash(Episode episode) throws InfoHashProviderException {
        LOGGER.debug("ENTER: getInfoHash(); episode={}", episode.toString());
        String infoHash = null;
        
        // Get instance of search interface and search for episode
        SearchInterface searchInterface = new PirateBaySearch();
        List<PirateBaySearchResultItem> resultList = searchInterface.search(episode);
        
        // TODO Sort and select first in the list
        
        LOGGER.debug("LEAVING: getInfoHash(); infoHash={}", infoHash);
        return infoHash;        
    }
    
}
