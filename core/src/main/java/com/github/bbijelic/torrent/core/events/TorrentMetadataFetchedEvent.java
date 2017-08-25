package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Torrent metadata fetched event
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentMetadataFetchedEvent {

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

	public TorrentMetadataFetchedEvent(final Torrent torrent) {
		this.torrent = torrent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TorrentMetadataFetchedEvent [torrent=");
		builder.append(torrent);
		builder.append("]");
		return builder.toString();
	}

}
