package com.github.bbijelic.torrent.core.torrents.file;

import java.io.File;

/**
 * Torrent file provider
 * 
 * @author Bojan Bijelić
 */
public interface TorrentFileProvider {
    
    /**
     * Returns torrent file for a given info hash
     * 
     * @param infoHash the info hash
     * @return the torrent file
     */
    File getTorrentFile(String infoHash);
    
}
