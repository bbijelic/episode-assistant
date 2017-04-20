package com.github.bbijelic.torrent.providers.torrents.foile.torrsave;

import java.io.File;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Torrsave torrent file provider test
 * 
 * @author Bojan Bijelić
 */
public class TorrsaveTorrentFileProviderTest {
    
    @Test
    public void testDownloadByInfoHash(){
        
        // Download directory
        File downloadDirectory = new File("/tmp");
        
        // File provider
        TorrsaveTorrentFileProvider fileProvider = new TorrsaveTorrentFileProvider(downloadDirectory);
        
        // Get the torrent file
        File torrentFile = fileProvider.getTorrentFile("5595A7267B6A65FF11355EE393BA9D58A73ECDAF");
        
        assertTrue(torrentFile.exists());
        
    }
    
}
