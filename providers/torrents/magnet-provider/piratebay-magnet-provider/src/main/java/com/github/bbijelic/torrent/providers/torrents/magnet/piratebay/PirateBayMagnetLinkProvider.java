package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.torrents.magnet.MagnetLinkProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.MagnetLinkProviderException;
import com.github.bbijelic.torrent.core.torrents.magnet.ResultItem;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort.MultiComparator;

/**
 * PirateBay.org magnet provider implementation
 * 
 * @author Bojan Bijelić
 */
public class PirateBayMagnetLinkProvider implements MagnetLinkProvider {
        
	/**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PirateBayMagnetLinkProvider.class);
	
    @Override
    public String getAuthor() {
        return "Bojan Bijelić";
    }
    
    @Override
    public String getDescription() {
        return "PirateBay.org torrent magnet provider";
    }
    
    @Override
    public String getName() {
        return "PirateBay.org Magnet Provider";
    }
            
    @Override
    public ResultItem getResultItem(final Episode episode, final List<Comparator<ResultItem>> comparators) throws MagnetLinkProviderException {
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
        return bestMatchResultItem;        
    }
    
}
