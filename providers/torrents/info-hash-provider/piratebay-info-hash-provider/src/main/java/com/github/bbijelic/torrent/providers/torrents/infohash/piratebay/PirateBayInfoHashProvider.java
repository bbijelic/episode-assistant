package com.github.bbijelic.torrent.providers.torrents.infohash.piratebay;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.torrents.infohash.InfoHashProvider;
import com.github.bbijelic.torrent.core.torrents.infohash.InfoHashProviderException;
import com.github.bbijelic.torrent.core.torrents.infohash.ResultItem;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.MultiComparator;

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
    public String getInfoHash(final Episode episode, final List<Comparator<ResultItem>> comparators) throws InfoHashProviderException {
        LOGGER.debug("ENTER: getInfoHash(); episode={}", episode.toString());
        
        // Get instance of search interface and search for episode
        SearchInterface searchInterface = new PirateBaySearch();
        List<PirateBaySearchResultItem> resultList = searchInterface.search(episode);
        
        // Multi comparator instance
        MultiComparator<ResultItem> multiComparator = 
            new MultiComparator<ResultItem>(comparators);
        
        // Sort the list by using comparators
        // Best match is on the last place
        Collections.sort(resultList, multiComparator);
        
        // Get the best
        ResultItem bestMatchResultItem = resultList.get(resultList.size()-1);
        
        LOGGER.debug("LEAVING: getInfoHash(); infoHash={}", bestMatchResultItem.getInfoHash());
        return bestMatchResultItem.getInfoHash();        
    }
    
}
