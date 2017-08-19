package com.github.bbijelic.torrent.providers.episodes.episodecalendar;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.EpisodeBatch;
import com.github.bbijelic.torrent.core.episodes.EpisodeProviderException;
import com.github.bbijelic.torrent.core.episodes.EpisodesProvider;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * EpisodeCalendar.cor episode provider
 * 
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public class EpisodeCalendarEpisodeProvider implements EpisodesProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(EpisodeCalendarEpisodeProvider.class);

	@Override
	public String getAuthor() {
		return "Bojan Bijelić";
	}

	@Override
	public String getDescription() {
		return "Episode calendar provider for the episodes to be downloaded";
	}

	@Override
	public String getName() {
		return "EpisodeCalendar.com Episode Provider";
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Set<EpisodeBatch> getEpisodes(Properties config) throws EpisodeProviderException {

		// Validate configuration parameter
		validateConfig(config);

		// Instance of episode batch set
		Set<EpisodeBatch> episodeBatchSet = new HashSet<EpisodeBatch>();

		// Get feed location
		String feedLocation = config.getProperty(PROPERTIES_RRS_LINK_PARAM_KEY);
		LOGGER.debug("Getting data from: {}", feedLocation);

		try {

			URL feedUrl = new URL(feedLocation);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));

			List entries = feed.getEntries();
			for (Object entryObj : entries) {
				SyndEntry entry = (SyndEntry) entryObj;

				EpisodeBatch episodeBatch = (EpisodeBatch) entry.getModule("");
				episodeBatchSet.add(episodeBatch);
			}

		} catch (Throwable cause) {
			StringBuilder exceptionMessage = new StringBuilder("Episode calendar provider failed to get data: ");
			exceptionMessage.append(cause.getMessage());
			LOGGER.error(exceptionMessage.toString(), cause);

			throw new EpisodeProviderException(exceptionMessage.toString());
		}

		// Return episode batch set
		return episodeBatchSet;
	}

	public static final String PROPERTIES_RSS_LINK_PROTOCOL = "protocol";
	public static final String PROPERTIES_RRS_LINK_PARAM_KEY = "rsslink";

	/**
	 * Validates given properties
	 * 
	 * @param config
	 * @throws EpisodeProviderException
	 */
	private void validateConfig(Properties config) throws EpisodeProviderException {
		LOGGER.debug("Validating provider configuration: {}", config.toString());

		// Check for episode calendar RSS link
		if (!config.containsKey(PROPERTIES_RRS_LINK_PARAM_KEY)) {
			StringBuilder exceptionMessage = new StringBuilder("Episode calendar configuration missing parameter: ");
			exceptionMessage.append(PROPERTIES_RRS_LINK_PARAM_KEY);

			LOGGER.error(exceptionMessage.toString());
			throw new EpisodeProviderException(exceptionMessage.toString());
		}

	}

	@Override
	public String toString() {
		return getName();
	}

}
