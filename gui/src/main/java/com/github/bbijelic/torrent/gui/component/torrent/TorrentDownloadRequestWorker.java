/**
 * 
 */
package com.github.bbijelic.torrent.gui.component.torrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.PirateBaySearch;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.PirateBaySearchResultItem;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort.KeywordComparator;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort.MultiComparator;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort.PopularityComparator;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort.SizeComparator;

import javafx.collections.ObservableList;

/**
 * Torrent download request worker
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentDownloadRequestWorker implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(TorrentDownloadRequestWorker.class);

	/**
	 * Episode
	 */
	private Episode episode;

	/**
	 * Observable list for the table view items
	 */
	private ObservableList<TorrentModel> itemsList;

	/**
	 * Constructor
	 * 
	 * @param itemsList
	 * @param episode
	 */
	public TorrentDownloadRequestWorker(ObservableList<TorrentModel> itemsList, Episode episode) {
		this.itemsList = itemsList;
		this.episode = episode;
	}

	@Override
	public void run() {

		// Keywords priority
		Map<String, Integer> keywordsPriority = new HashMap<String, Integer>();
		keywordsPriority.put("KILLERS", 2);
		keywordsPriority.put("FUM", 2);
		keywordsPriority.put("MTB", 2);
		keywordsPriority.put("DEFLATE", 1);
		keywordsPriority.put("LOL", 3);
		keywordsPriority.put("FLEET", 1);
		keywordsPriority.put("DIMENSION", 1);
		keywordsPriority.put("[ettv]", 2);
		keywordsPriority.put("x264", 3);

		// Quality priority
		Map<String, Integer> qualityPriority = new HashMap<String, Integer>();
		qualityPriority.put("HDTV", 3);
		qualityPriority.put("WEBRIP", 2);
		qualityPriority.put("WEB-RIP", 2);
		qualityPriority.put("WEBDL", 2);
		qualityPriority.put("WEB DL", 2);
		qualityPriority.put("WEB-DL", 2);
		qualityPriority.put("x265", 0);
		qualityPriority.put("HEVC", 0);

		// Pirate bay search instance
		PirateBaySearch pirateBaySearch = new PirateBaySearch();
		List<PirateBaySearchResultItem> searchResults = pirateBaySearch.search(episode);

		if (searchResults.isEmpty()) {
			LOGGER.info("Episode not found: {}", episode.toString());
			return;
		}

		// List of comparators
		List<Comparator<PirateBaySearchResultItem>> comparatorList = new ArrayList<Comparator<PirateBaySearchResultItem>>();

		comparatorList.add(new KeywordComparator(keywordsPriority));
		comparatorList.add(new KeywordComparator(qualityPriority));
		comparatorList.add(new PopularityComparator());
		comparatorList.add(new SizeComparator());

		// Multi comparator instance
		MultiComparator<PirateBaySearchResultItem> multiComparator = new MultiComparator<PirateBaySearchResultItem>(
				comparatorList);

		Collections.sort(searchResults, multiComparator);

		PirateBaySearchResultItem resultItem = searchResults.get(0);
		if (resultItem != null) {
			LOGGER.debug("Preparing to download: {}", resultItem.toString());

			itemsList.add(new TorrentModel(episode.getShowName(), episode.getEpisodeName(), episode.getSeasonNumber(),
					episode.getEpisodeNumber(), episode.getSummary(), resultItem.getName()));
		}

	}

}
