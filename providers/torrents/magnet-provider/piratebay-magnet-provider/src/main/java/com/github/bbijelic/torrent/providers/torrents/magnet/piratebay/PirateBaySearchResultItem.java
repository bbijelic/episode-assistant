package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay;

import java.util.Calendar;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Pirate bay search result item
 * 
 * @author Bojan Bijelić
 */
public class PirateBaySearchResultItem implements Torrent {

	/**
	 * Constructor
	 * 
	 * @param type
	 *            the torrent type
	 * @param name
	 *            the torrent name
	 * @param uploaded
	 *            the time of torrent upload
	 * @param size
	 *            the torrent size
	 * @param uploadedBy
	 *            the torrent author
	 * @param seeders
	 *            the number of torrent seeders
	 * @param leechers
	 *            the number of torrent leechers
	 * @param magnetLink
	 *            the torrent magnet link
	 * @param infoHash
	 *            the torrent info hash
	 */
	public PirateBaySearchResultItem(final String type, final String name, final Calendar uploaded, final long size,
			final String uploadedBy, final int seeders, final int leechers, final String magnetLink,
			final String infoHash) {

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

	@Override
	public String getType() {
		return type;
	}

	/**
	 * Name of the torrent
	 */
	private String name;

	@Override
	public String getName() {
		return name;
	}

	/**
	 * When is uploaded
	 */
	private Calendar uploaded;

	@Override
	public Calendar getUploaded() {
		return uploaded;
	}

	/**
	 * Torrent size in bytes
	 */
	private long size;

	@Override
	public long getSize() {
		return size;
	}

	/**
	 * Author of torrent
	 */
	private String uploadedBy;

	@Override
	public String getUploadedBy() {
		return uploadedBy;
	}

	/**
	 * Number of seeders
	 */
	private int seeders;

	@Override
	public int getSeeders() {
		return seeders;
	}

	/**
	 * The number of leechers
	 */
	private int leechers;

	@Override
	public int getLeechers() {
		return leechers;
	}

	/**
	 * Torrent info hash
	 */
	private String infoHash;

	@Override
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infoHash == null) ? 0 : infoHash.hashCode());
		result = prime * result + leechers;
		result = prime * result + ((magnetLink == null) ? 0 : magnetLink.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + seeders;
		result = prime * result + (int) (size ^ (size >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uploaded == null) ? 0 : uploaded.hashCode());
		result = prime * result + ((uploadedBy == null) ? 0 : uploadedBy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PirateBaySearchResultItem other = (PirateBaySearchResultItem) obj;
		if (infoHash == null) {
			if (other.infoHash != null)
				return false;
		} else if (!infoHash.equals(other.infoHash))
			return false;
		if (leechers != other.leechers)
			return false;
		if (magnetLink == null) {
			if (other.magnetLink != null)
				return false;
		} else if (!magnetLink.equals(other.magnetLink))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (seeders != other.seeders)
			return false;
		if (size != other.size)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uploaded == null) {
			if (other.uploaded != null)
				return false;
		} else if (!uploaded.equals(other.uploaded))
			return false;
		if (uploadedBy == null) {
			if (other.uploadedBy != null)
				return false;
		} else if (!uploadedBy.equals(other.uploadedBy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PirateBaySearchResultItem [type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", uploaded=");
		builder.append(uploaded);
		builder.append(", size=");
		builder.append(size);
		builder.append(", uploadedBy=");
		builder.append(uploadedBy);
		builder.append(", seeders=");
		builder.append(seeders);
		builder.append(", leechers=");
		builder.append(leechers);
		builder.append(", infoHash=");
		builder.append(infoHash);
		builder.append(", magnetLink=");
		builder.append(magnetLink);
		builder.append("]");
		return builder.toString();
	}
}
