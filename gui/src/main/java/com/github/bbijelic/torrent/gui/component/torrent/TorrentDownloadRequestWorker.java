/**
 * 
 */
package com.github.bbijelic.torrent.gui.component.torrent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.StartDownloadTorrentEvent;
import com.github.bbijelic.torrent.core.torrents.magnet.MagnetLinkProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.MagnetLinkProviderException;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.providers.torrents.magnet.piratebay.sort.KeywordComparator;
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
		keywordsPriority.put("FUM", 3);
		keywordsPriority.put("MTB", 2);
		keywordsPriority.put("DEFLATE", 1);
		keywordsPriority.put("LOL", 3);
		keywordsPriority.put("FLEET", 2);
		keywordsPriority.put("DIMENSION", 2);
		keywordsPriority.put("[ettv]", 2);
		keywordsPriority.put("SVA", 2);
		keywordsPriority.put("AVS", 3);

		// Quality priority
		Map<String, Integer> qualityPriority = new HashMap<String, Integer>();
		qualityPriority.put("720p", 5);
		qualityPriority.put("1080p", 10);
		qualityPriority.put("HDTV", 3);
		qualityPriority.put("WEBRIP", 2);
		qualityPriority.put("WEB-RIP", 2);
		qualityPriority.put("WEBDL", 2);
		qualityPriority.put("WEB DL", 2);
		qualityPriority.put("WEB-DL", 2);
		qualityPriority.put("x264", 5);
		qualityPriority.put("x265", 0);
		qualityPriority.put("HEVC", 0);

		// List of comparators
		List<Comparator<Torrent>> comparatorList = new ArrayList<Comparator<Torrent>>();
		comparatorList.add(new KeywordComparator(keywordsPriority));
		comparatorList.add(new KeywordComparator(qualityPriority));
		comparatorList.add(new PopularityComparator());
		comparatorList.add(new SizeComparator());

		// Instance of service loader for the magnet link providers
		ServiceLoader<MagnetLinkProvider> episodeProviderServiceLoader = ServiceLoader.load(MagnetLinkProvider.class);
		episodeProviderServiceLoader.forEach(new Consumer<MagnetLinkProvider>() {

			@Override
			public void accept(MagnetLinkProvider magnetLinkProvider) {
				LOGGER.debug("Handling magnet link provider: {}", magnetLinkProvider.toString());

				try {

					// Find the torrent (magnet link) for the episode
					Optional<Torrent> torrentOptional = magnetLinkProvider.getResultItem(episode, comparatorList);
					
					if(torrentOptional.isPresent()) {
						// If torrent found
						Torrent torrent = torrentOptional.get();
						LOGGER.debug("Starting download: {}", torrent.toString());
						// Add torrent to the table
						itemsList.add(new TorrentModel(torrent));
						// Post event to start downloading torrent
						Events.getInstance().post(new StartDownloadTorrentEvent(torrent));
					}

				} catch (MagnetLinkProviderException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		});

	}

}
