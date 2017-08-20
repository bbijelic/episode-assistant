package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.episodes.Episode;

/**
 * Find torrent event
 * 
 * @author Bojan Bijelic
 *
 */
public class FindTorrentEvent {

	/**
	 * Episode
	 */
	private Episode episode;

	/**
	 * Constructor
	 * 
	 * @param episode
	 *            the episode
	 */
	public FindTorrentEvent(final Episode episode) {
		this.episode = episode;
	}

	/**
	 * Episode getter
	 * 
	 * @return the episode
	 */
	public Episode getEpisode() {
		return this.episode;
	}

}
