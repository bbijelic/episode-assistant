package com.github.bbijelic.torrent.core.events;

import com.github.bbijelic.torrent.core.episodes.Episode;

/**
 * Search episode event
 */
public class SearchEpisodeEvent {

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
	public SearchEpisodeEvent(final Episode episode) {
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
