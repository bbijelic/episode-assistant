package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.PirateBaySearchResultItem;

/**
 * Keyword comparator
 * 
 * @author Bojan Bijelić
 */
public class KeywordComparator implements Comparator<PirateBaySearchResultItem> {
    
    /**
     * Keyword priority
     */
    Map<String, Integer> keywordPrioirty = new HashMap<String, Integer>();
    
    /**
     * Constructor
     * 
     * @param keywordPrioirty the keyword priority map
     */
    public KeywordComparator(final Map<String, Integer> keywordPrioirty){
        this.keywordPrioirty = keywordPrioirty;
    }
    
    @Override
    public int compare(PirateBaySearchResultItem o1, PirateBaySearchResultItem o2) {
        String o1name = o1.getName().toLowerCase().trim();
        String o2name = o2.getName().toLowerCase().trim();
        
        int o1score = 0;
        int o2score = 0;
        
        Iterator<Map.Entry<String, Integer>> it = keywordPrioirty.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
            
            if(o1name.contains(pair.getKey().toLowerCase().trim())) o1score += pair.getValue();
            if(o2name.contains(pair.getKey().toLowerCase().trim())) o2score += pair.getValue();
            
            it.remove();
        }
        
        // Compare scores
        if(o1score == o2score) return 0;
        else return (o1score > o2score) ? 1 : -1;
    }
    
}
