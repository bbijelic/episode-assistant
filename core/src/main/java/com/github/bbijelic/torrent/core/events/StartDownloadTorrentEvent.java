package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Download torrent event
 * 
 * @author Bojan Bijelic
 *
 */
public class StartDownloadTorrentEvent {

	/**
	 * Torrent
	 */
	private Torrent torrent;

	/**
	 * Construcor
	 * 
	 * @param torrent
	 *            the torrent
	 */
	public StartDownloadTorrentEvent(final Torrent torrent) {
		this.torrent = torrent;
	}

	/**
	 * Torrent getter
	 * 
	 * @return the torrent
	 */
	public Torrent getTorrent() {
		return this.torrent;
	}

}
