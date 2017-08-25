package com.github.bbijelic.torrent.core.torrents.magnet;

/**
 * Torrent state
 * 
 * @author Bojan Bijelic
 *
 */
public enum TorrentState {

	STOPPED("Stopped"),

	DOWNLOADING("Downloading"),

	FETCHING_METADATA("Fetching metadata"),

	FINISHED("Finished");

	private String value;

	/**
	 * Constructor
	 * 
	 * @param value
	 *            the value
	 */
	TorrentState(String value) {
		this.value = value;
	}

	/**
	 * Value getter
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
