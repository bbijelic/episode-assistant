package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort;

import java.util.Comparator;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Popularity comparator. Compares seeder numbers.
 * 
 * @author Bojan Bijelić
 */
public class PopularityComparator implements Comparator<Torrent> {
    
    @Override
    public int compare(Torrent o1, Torrent o2) {
        if(o1.getSeeders() == o2.getSeeders()) return 0;
        else return ( o1.getSeeders() > o2.getLeechers() ) ? 1 : -1;
    }
    
}
