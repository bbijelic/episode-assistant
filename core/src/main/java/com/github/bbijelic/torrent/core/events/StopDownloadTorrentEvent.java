package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Stop download torrent event
 * 
 * @author Bojan Bijelic
 *
 */
public class StopDownloadTorrentEvent {

	/**
	 * Torrent
	 */
	private Torrent torrent;

	/**
	 * Torrent getter
	 * 
	 * @return the torrent
	 */
	public Torrent getTorrent() {
		return torrent;
	}

	/**
	 * Constructor
	 * 
	 * @param torrent
	 *            the torrent
	 */
	public StopDownloadTorrentEvent(final Torrent torrent) {
		this.torrent = torrent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StopDownloadTorrentEvent [torrent=");
		builder.append(torrent);
		builder.append("]");
		return builder.toString();
	}

}
