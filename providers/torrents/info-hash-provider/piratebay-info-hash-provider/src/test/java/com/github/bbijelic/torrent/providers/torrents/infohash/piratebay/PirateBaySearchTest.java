package com.github.bbijelic.torrent.providers.torrents.infohash.piratebay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.KeywordComparator;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.MultiComparator;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.PopularityComparator;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.SizeComparator;

/**
 * Pirate bay search test
 * 
 * @author Bojan Bijelić
 */
public class PirateBaySearchTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PirateBaySearchTest.class);
    
    @Test
    public void searchTest(){
        
        // Pirate bay search instance
        PirateBaySearch pirateBaySearch = new PirateBaySearch();
        
        // Test episode
        Episode episode = new EpisodeTestImpl("Scorpion", "Rock Block", 3, 21, "No summary");
        
        List<PirateBaySearchResultItem> searchResults = pirateBaySearch.search(episode);
        assertFalse(searchResults.isEmpty());
        
        // Keywords priority
        Map<String, Integer> keywordsPriority = new HashMap<String, Integer>();
        keywordsPriority.put("KILLERS", 2);
        keywordsPriority.put("FUM", 2);
        keywordsPriority.put("MTB", 2);
        keywordsPriority.put("DEFLATE", 1);
        keywordsPriority.put("LOL", 3);
        keywordsPriority.put("FLEET", 1);
        keywordsPriority.put("DIMENSION", 1); 
        keywordsPriority.put("[ettv]", 2);
        
        // Quality priority
        Map<String, Integer> qualityPriority = new HashMap<String, Integer>();
        qualityPriority.put("HDTV", 3);
        qualityPriority.put("WEBRIP", 2);
        qualityPriority.put("WEB-RIP", 2);
        qualityPriority.put("WEBDL", 2);
        qualityPriority.put("WEB DL", 2);
        qualityPriority.put("WEB-DL", 2);
        
        // List of comparators
        List<Comparator<PirateBaySearchResultItem>> comparatorList 
            = new ArrayList<Comparator<PirateBaySearchResultItem>>();
            
        comparatorList.add(new KeywordComparator(keywordsPriority));
        comparatorList.add(new KeywordComparator(qualityPriority));
        comparatorList.add(new PopularityComparator());
        comparatorList.add(new SizeComparator());
        
        // Multi comparator instance
        MultiComparator<PirateBaySearchResultItem> multiComparator = 
            new MultiComparator<PirateBaySearchResultItem>(comparatorList);
            
        Collections.sort(searchResults, multiComparator);
        
        LOGGER.debug("Best match: {}", searchResults.get(searchResults.size()-1).toString());
    }
    
    @Test
    public void testInfoHashMagnetLinkRegexPattern(){
        
        String magnetLink = "magnet:?xt=urn:btih:244d975d1f4f9134db23ef8656b359e2edb5b628&dn=The.Last.Kingdom.S02E05.1080p.HEVC.x265-MeGusta&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969";
        Pattern pattern = Pattern.compile("[0-9a-fA-F]{40}");
        
        Matcher matcher = pattern.matcher(magnetLink);
        if(matcher.find()){
            String infoHash = matcher.group();
            assertEquals("244d975d1f4f9134db23ef8656b359e2edb5b628", infoHash);
        }
        
    }
    
    @Test
    public void testStringFormat(){
        final String format = "s%02de%02d";
        String formattedString1 = String.format(format, 2, 5);
        assertEquals("s02e05", formattedString1);
        
        String formattedString2 = String.format(format, 2, 15);
        assertEquals("s02e15", formattedString2);
    }
        
}
