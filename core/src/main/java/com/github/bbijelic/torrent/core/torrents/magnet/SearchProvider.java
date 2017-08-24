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
	 * 
	 * @param episode
	 *            the episode
	 * @return the list of torrents
	 * @throws SearchProviderException
	 */
	List<Torrent> search(Episode episode) throws SearchProviderException;

	/**
	 * Search by search input string
	 * 
	 * @param input
	 *            the input string
	 * @return the list of torrents
	 * @throws SearchProviderException
	 */
	List<Torrent> search(String input) throws SearchProviderException;

}
