package com.github.bbijelic.torrent.gui.main.controller;

/**
 * Episode status enumeration
 * 
 * @author Bojan Bijelic <bojan.bijelic.os@gmail.com>
 *
 */
public enum EpisodeStaus {

	/**
	 * Downloading subtitle state
	 */
	DOWNLOADING_SUBTITLE,
	
	/**
	 * Downloading torrent state
	 */
	DOWNLOADING_TORRENT,
	
	/**
	 * Finished state
	 */
	FINISHED,
	
	/**
	 * Torrent not found state
	 */
	TORRENT_NOT_FOUND,
	
	/**
	 * Not started state
	 */
	NOT_STARTED;
	
}
