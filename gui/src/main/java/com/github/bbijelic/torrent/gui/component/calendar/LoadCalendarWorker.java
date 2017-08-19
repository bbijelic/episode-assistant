/**
 * 
 */
package com.github.bbijelic.torrent.gui.component.calendar;

import java.util.Properties;
import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.episodes.EpisodeBatch;
import com.github.bbijelic.torrent.core.episodes.EpisodeProviderException;
import com.github.bbijelic.torrent.core.episodes.EpisodesProvider;

import javafx.collections.ObservableList;

/**
 * Load Calendar worker
 * 
 * @author Bojan Bijelic
 *
 */
public class LoadCalendarWorker implements Runnable {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoadCalendarWorker.class);

	/**
	 * Observable list for the table view
	 */
	private ObservableList<EpisodeModel> episodeList;

	/**
	 * Constructor
	 * 
	 * @param dataList
	 */
	public LoadCalendarWorker(ObservableList<EpisodeModel> episodeList) {
		this.episodeList = episodeList;
	}

	@Override
	public void run() {
		LOGGER.debug("Executing load calendar worker");

		// TODO Get properties from database
		Properties config = new Properties();
		config.setProperty("rsslink", "https://episodecalendar.com/en/rss_feed/bojan.bijelic.os@gmail.com");

		// Instance of service loader for the epsisode providers
		ServiceLoader<EpisodesProvider> episodeProviderServiceLoader = ServiceLoader.load(EpisodesProvider.class);
		episodeProviderServiceLoader.forEach(new Consumer<EpisodesProvider>() {

			@Override
			public void accept(EpisodesProvider episodeProvider) {
				LOGGER.debug("Handling episode provider: {}", episodeProvider.toString());

				try {

					for (EpisodeBatch episodeBatch : episodeProvider.getEpisodes(config)) {
						for (Episode episode : episodeBatch.getEpisodes()) {

							episodeList.add(new EpisodeModel(episode.getShowName(), episode.getEpisodeName(),
									episode.getSeasonNumber(), episode.getEpisodeNumber(), episode.getSummary(),
									episodeBatch.getDate()));
						}
					}

				} catch (EpisodeProviderException e) {
					LOGGER.error(e.getMessage(), e);
				}

			}
		});

	}

}
