package com.github.bbijelic.torrent.core.torrents.magnet;

import java.util.List;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.provider.Provider;

/**
 * Search provider
 * 
 * @author Bojan Bijelic
 *
 */
public interface SearchProvider extends Provider {

	/**
	 * Search by episode
	 * @param episode the episode
	 * @return the list of torrents
	 */
	List<Torrent> search(Episode episode);
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	List<Torrent> search(String input);
	
}
