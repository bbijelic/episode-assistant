package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort;

import java.util.Comparator;

import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.PirateBaySearchResultItem;

public class SizeComparator implements Comparator<PirateBaySearchResultItem> {
    
    @Override
    public int compare(PirateBaySearchResultItem o1, PirateBaySearchResultItem o2) {
        // Smaller is better
        if(o1.getSize() == o2.getSize()) return 0;
        else return ( o1.getSize() < o2.getSize() ) ? 1 : -1;
    }
    
}
