package com.github.bbijelic.torrent.core.source;

import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Item {
        
    private Set<Episode> episodes;
    
    public Set<Episode> getEpisodes() {
        return episodes;
    }
    
    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }
    
}
