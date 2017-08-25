package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Torrent finished event
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentFinishedEvent {

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

	public TorrentFinishedEvent(final Torrent torrent) {
		this.torrent = torrent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TorrentFinishedEvent [torrent=");
		builder.append(torrent);
		builder.append("]");
		return builder.toString();
	}

}
