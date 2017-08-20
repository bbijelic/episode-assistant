package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Torrent progress event
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentProgressEvent {

	private double progress;

	private Torrent torrent;

	public TorrentProgressEvent(final double progress, final Torrent torrent) {
		this.torrent = torrent;
		this.progress = progress;
	}

	public double getProgress() {
		return progress;
	}

	public Torrent getTorrent() {
		return torrent;
	}

}
