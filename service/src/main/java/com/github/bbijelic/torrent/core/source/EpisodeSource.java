package com.github.bbijelic.torrent.core.source;

import java.util.Set;

/**
 * Episode source interface
 * 
 * @author Bojan Bijelić
 */
public interface EpisodeSource {
    
    /**
     * Gets episode list from episode source
     */
    Set<Episode> getEpisodes() throws Exception;
    
}
