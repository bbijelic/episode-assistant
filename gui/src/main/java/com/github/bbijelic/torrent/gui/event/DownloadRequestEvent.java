package com.github.bbijelic.torrent.gui.event;

import com.github.bbijelic.torrent.core.episodes.Episode;

/**
 * Download request event
 * 
 * @author Bojan Bijelic
 *
 */
public class DownloadRequestEvent {

	private Episode episode;

	public DownloadRequestEvent(Episode episode) {
		this.episode = episode;
	}

	public Episode getEpisode() {
		return episode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DownloadRequestEvent [episode=");
		builder.append(episode);
		builder.append("]");
		return builder.toString();
	}

}
