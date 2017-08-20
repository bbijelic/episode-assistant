package com.github.bbijelic.torrent.core.torrents.magnet;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.provider.Provider;

/**
 * Magnet provider for the episode.
 * 
 * @author Bojan Bijelić
 */
public interface MagnetLinkProvider extends Provider {

	/**
	 * Finds best match result item for a given episode
	 * 
	 * @param episode
	 *            the episode information
	 * @param comparators
	 *            the list of comparators to filter best match for the info hash
	 * @return the optional of torrent
	 * @throws MagnetLinkProviderException
	 */
	Optional<Torrent> getResultItem(final Episode episode, final List<Comparator<Torrent>> comparators)
			throws MagnetLinkProviderException;

}
