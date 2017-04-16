package com.github.bbijelic.torrent.providers.episodes.episodecalendar;

import static org.junit.Assert.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import com.github.bbijelic.torrent.core.episodes.EpisodeBatch;
import com.github.bbijelic.torrent.core.episodes.EpisodeProviderException;
import com.github.bbijelic.torrent.core.episodes.EpisodesProvider;

/**
 * Episode calendar episode provider test
 *
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public class EpisodeCalendarEpisodeProviderTest {

	@Test
	public void parsePubDate() {

		try {

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(EpisodeCalendarModuleParser.ITEM_PUB_DATE_FORMAT,
					Locale.ENGLISH);
			simpleDateFormat.parse("Fri, 07 Apr 2017 00:00:00 GMT");

		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}

	@Test(expected = EpisodeProviderException.class)
	public void invalidConfig() throws EpisodeProviderException {
		Properties config = new Properties();
		EpisodesProvider provider = new EpisodeCalendarEpisodeProvider();
		provider.getEpisodes(config);
	}

	@Test
	public void getEpisodes() {

		try {

			URL rssUrl = getClass().getClassLoader().getResource("rss_example.xml");

			Properties config = new Properties();
			config.put(EpisodeCalendarEpisodeProvider.PROPERTIES_RRS_LINK_PARAM_KEY, rssUrl.toString());

			EpisodesProvider provider = new EpisodeCalendarEpisodeProvider();
			Set<EpisodeBatch> days = provider.getEpisodes(config);

			assertEquals(6, days.size());

			assertFalse(days.isEmpty());

		} catch (EpisodeProviderException epe) {
			fail(epe.getMessage());
		}

	}

}
