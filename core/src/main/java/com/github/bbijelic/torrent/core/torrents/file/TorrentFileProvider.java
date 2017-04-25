package com.github.bbijelic.torrent.core.torrents.file;

import java.io.File;

import com.github.bbijelic.torrent.core.provider.Provider;

/**
 * Torrent file provider
 * 
 * @author Bojan Bijelić
 */
public interface TorrentFileProvider extends Provider {
    
    /**
     * Returns torrent file for a given info hash
     * 
     * @param infoHash the info hash
     * @throws TorrentFileProviderException
     * @return the torrent file
     */
    File getTorrentFile(String infoHash) throws TorrentFileProviderException;
    
}
