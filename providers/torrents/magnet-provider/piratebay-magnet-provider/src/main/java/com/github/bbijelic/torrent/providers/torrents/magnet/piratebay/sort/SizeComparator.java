package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort;

import java.util.Comparator;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

public class SizeComparator implements Comparator<Torrent> {
    
    @Override
    public int compare(Torrent o1, Torrent o2) {
        // Smaller is better
        if(o1.getSize() == o2.getSize()) return 0;
        else return ( o1.getSize() < o2.getSize() ) ? 1 : -1;
    }
    
}
