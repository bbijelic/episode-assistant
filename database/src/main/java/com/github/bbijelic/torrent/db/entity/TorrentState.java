package com.github.bbijelic.torrent.db.entity;

/**
 * Torrent state
 * 
 * @author Bojan Bijelic
 *
 */
public enum TorrentState {

	/**
	 * Torrent metadata not yet loaded
	 */
	GETTING_METADATA,

	/**
	 * Download is in progress
	 */
	DOWNLOADING,

	/**
	 * Torrent is downloaded
	 */
	FINISHED,

	/**
	 * Torrent download is stopped
	 */
	STOPPED,

	/**
	 * Torrent download is paused
	 */
	PAUSED

}
