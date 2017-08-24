package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.torrents.magnet.MagnetLinkProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.MagnetLinkProviderException;
import com.github.bbijelic.torrent.core.torrents.magnet.SearchProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.SearchProviderException;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort.MultiComparator;

/**
 * PirateBay.org magnet provider implementation
 * 
 * @author Bojan Bijelić
 */
public class PirateBayMagnetLinkProvider implements MagnetLinkProvider {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PirateBayMagnetLinkProvider.class);

	@Override
	public String getAuthor() {
		return "Bojan Bijelić";
	}

	@Override
	public String getDescription() {
		return "PirateBay.org torrent magnet provider";
	}

	@Override
	public String getName() {
		return "PirateBay.org Magnet Provider";
	}

	@Override
	public Optional<Torrent> getResultItem(final Episode episode, final List<Comparator<Torrent>> comparators)
			throws MagnetLinkProviderException {
		LOGGER.debug("ENTER: getInfoHash(); episode={}", episode.toString());

		// Get instance of search interface and search for episode
		SearchProvider searchInterface = new PirateBaySearchProvider();
		List<Torrent> resultList;

		try {

			// Do the search
			resultList = searchInterface.search(episode);
		} catch (SearchProviderException e) {
			throw new MagnetLinkProviderException(getName() + " failed to obtain torrent information", e);
		}

		// Multi comparator instance
		MultiComparator<Torrent> multiComparator = new MultiComparator<Torrent>(comparators);

		// Sort the list by using comparators
		// Best match is on the last place
		Collections.sort(resultList, multiComparator);

		// Get the best match torrent
		Torrent torrent = resultList.get(resultList.size() - 1);

		LOGGER.debug("LEAVING: getResultItem(); torrent={}", torrent.getInfoHash());
		return Optional.ofNullable(torrent);
	}

}
