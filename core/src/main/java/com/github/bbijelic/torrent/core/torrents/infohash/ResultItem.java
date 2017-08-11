package com.github.bbijelic.torrent.core.torrents.infohash;

import java.util.Calendar;

/**
 * Result item
 * 
 * @author Bojan Bijelić
 */
public interface ResultItem {
    
    /**
     * Returns the result item name
     * 
     * @return the name
     */
    String getName();
    
    /**
     * Returns the result item type
     * 
     * @return the type
     */
    String getType();
    
    /**
     * Returns the result item uploaded calendar
     * 
     * @return the calendar
     */
    Calendar getUploaded();
    
    /**
     * Returns the result item size in bytes
     * 
     * @return the size
     */
    long getSize();
    
    /**
     * Returns the author of the result item
     * 
     * @return the author
     */
    String getUploadedBy();
    
    /**
     * Returns the number of seeders
     * 
     * @return the number of seeders
     */
    int getSeeders();
    
    /**
     * Returns the number of leechers
     * 
     * @return the number of leechers
     */
    int getLeechers();
    
    /**
     * Returns the info hash of the result item
     * 
     * @return the info hash
     */
    String getInfoHash();
    
    /**
     * Returns the magnet link of the result item
     * 
     * @return the magnet link
     * @return
     */
    String getMagnetLink();
    
}
