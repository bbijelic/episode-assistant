package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Stop download torrent event
 * 
 * @author Bojan Bijelic
 *
 */
public class StopDownloadTorrentEvent {

	private Torrent torrent;

	public StopDownloadTorrentEvent(final Torrent torrent) {
		this.torrent = torrent;
	}

	/**
	 * Torrent getter
	 * 
	 * @return the torrent
	 */
	public Torrent getTorrent() {
		return torrent;
	}

}
