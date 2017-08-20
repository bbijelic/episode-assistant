package com.github.bbijelic.torrent.gui.component.torrent.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.torrents.magnet.SearchProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Torrent search service
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentSearchService extends Service<List<Torrent>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TorrentSearchService.class);

	/**
	 * Search input
	 */
	private String searchInput;

	/**
	 * Search provider
	 */
	private SearchProvider searchProvider;

	/**
	 * Constructor
	 * 
	 * @param searchInput
	 *            the search input
	 * @param searchProvider
	 *            the search provider
	 */
	public TorrentSearchService(final String searchInput, final SearchProvider searchProvider) {
		this.searchInput = searchInput;
		this.searchProvider = searchProvider;
	}

	@Override
	protected Task<List<Torrent>> createTask() {
		return new Task<List<Torrent>>() {

			@Override
			protected List<Torrent> call() throws Exception {
				return searchProvider.search(searchInput);
			}

		};
	}

}
