package com.github.bbijelic.torrent.providers.torrents.infohash.piratebay;

import java.util.Calendar;

/**
 * Pirate bay search result item
 * 
 * @author Bojan Bijelić
 */
public class PirateBaySearchResultItem {
    
    /**
     * Constructor
     * 
     * @param type the torrent type
     * @param name the torrent name
     * @param uploaded the time of torrent upload
     * @param size the torrent size
     * @param uploadedBy the torrent author
     * @param seeders the number of torrent seeders
     * @param leechers the number of torrent leechers
     * @param magnetLink the torrent magnet link
     * @param infoHash the torrent info hash
     */
    public PirateBaySearchResultItem(
        final String type, final String name, final Calendar uploaded, 
        final long size, final String uploadedBy, final int seeders, 
        final int leechers, final String magnetLink, final String infoHash){
        
        this.type = type;
        this.name = name;
        this.uploaded = uploaded;
        this.size = size;
        this.uploadedBy = uploadedBy;
        this.seeders = seeders;
        this.leechers = leechers;
        this.magnetLink = magnetLink; 
        this.infoHash = infoHash;
    }
    
    /**
     * Type
     */
    private String type;
    
    /**
     * Type getter
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Name of the torrent
     */
    private String name;
    
    /**
     * Name getter
     * 
     * @return the torrent name
     */
    public String getName() {
        return name;
    }
    
    /**
     * When is uploaded
     */
    private Calendar uploaded;
    
    /**
     * Uploaded calendar getter
     * 
     * @return the uploaded calendar
     */
    public Calendar getUploaded() {
        return uploaded;
    }
    
    /**
     * Torrent size in bytes
     */
    private long size;
    
    /**
     * Size getter
     * 
     * @return the torrent size
     */
    public long getSize() {
        return size;
    }
    
    /**
     * Author of torrent
     */
    private String uploadedBy;
    
    /**
     * Uploaded by getter
     * 
     * @return the author of the torrent
     */
    public String getUploadedBy() {
        return uploadedBy;
    }
    
    /**
     * Number of seeders
     */
    private int seeders;
    
    /**
     * Seeders getter
     * 
     * @return the seeders number
     */
    public int getSeeders() {
        return seeders;
    }
    
    /**
     * The number of leechers
     */
    private int leechers;
    
    /**
     * Leechers getter
     * 
     * @return the leechers number
     */
    public int getLeechers() {
        return leechers;
    }
    
    /**
     * Torrent info hash
     */
    private String infoHash;
    
    /**
     * Info hash getter
     * 
     * @return the torrent info hash
     */
    public String getInfoHash() {
        return infoHash;
    }
    
    /**
     * The magnet link
     */
    private String magnetLink;
    
    /**
     * Magnet link getter
     * 
     * @return the magnet link
     */
    public String getMagnetLink() {
        return magnetLink;
    }
    
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PirateBaySearchResultItem [type=").append(type)
		.append(", name=").append(name)
		.append(", uploaded=").append(uploaded.getTime())
		.append(", size=").append(size)
		.append(", uploadedBy=").append(uploadedBy)
		.append(", seeders=").append(seeders)
		.append(", leechers=").append(leechers)
		.append(", infoHash=").append(infoHash)
		.append(", magnetLink=").append(magnetLink)
		.append("]");
		return builder.toString();
	}
    
}
