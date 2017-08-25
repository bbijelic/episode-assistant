package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.core.torrents.magnet.TorrentSpeed;

/**
 * Torrent speed event
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentSpeedEvent {

	/**
	 * Torrent speed
	 */
	private TorrentSpeed torrentSpeed;

	/**
	 * Torrent
	 */
	private Torrent torrent;

	/**
	 * Constructor
	 * 
	 * @param torrent
	 *            the torrent
	 * @param torrentSpeed
	 *            the torrent speed
	 */
	public TorrentSpeedEvent(final Torrent torrent, final TorrentSpeed torrentSpeed) {
		this.torrent = torrent;
		this.torrentSpeed = torrentSpeed;
	}

	/**
	 * Torrent speed getter
	 * 
	 * @return the torrent speed
	 */
	public TorrentSpeed getTorrentSpeed() {
		return torrentSpeed;
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
