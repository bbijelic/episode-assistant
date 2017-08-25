package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Selected torrent event
 * 
 * @author Bojan Bijelic
 *
 */
public class SelectedTorrentEvent {

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

	public SelectedTorrentEvent(final Torrent torrent) {
		this.torrent = torrent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectedTorrentEvent [torrent=");
		builder.append(torrent);
		builder.append("]");
		return builder.toString();
	}

}
