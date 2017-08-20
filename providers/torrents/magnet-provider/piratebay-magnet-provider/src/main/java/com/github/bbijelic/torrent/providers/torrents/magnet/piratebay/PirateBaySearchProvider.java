package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.torrents.magnet.SearchProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * PirateBay search handler implementation
 * 
 * @author Bojan Bijelić
 */
public class PirateBaySearchProvider implements SearchProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(PirateBaySearchProvider.class);

	/**
	 * The pirate bay search url template
	 */
	private static final String PIRATE_BAY_SEARCH_URL_TMPL = "https://thepiratebay.org/search/{SEARCH_SLUG}/0/7/0";

	/**
	 * Info hash regex pattern
	 */
	public static final String INFOHASH_REGEX_PATTERN = "[0-9a-fA-F]{40}";

	/**
	 * Supported formats
	 */
	private List<String> supportedDateFormats;

	/**
	 * Constructor
	 */
	public PirateBaySearchProvider() {
		// Format strings
		supportedDateFormats = new ArrayList<String>(2);
		supportedDateFormats.add("MM-dd HH:mm");
		supportedDateFormats.add("MM-dd yyyy HH:mm");
		supportedDateFormats.add("MM-dd yyyy");
	}

	@Override
	public List<Torrent> search(final Episode episode) {
		LOGGER.debug("ENTER: search(); episode={}", episode.toString());

		StringBuilder searchSlugBuilder = new StringBuilder();
		searchSlugBuilder.append(episode.getShowName().replace(" ", ".").toLowerCase());
		searchSlugBuilder.append(".");
		searchSlugBuilder.append(String.format("s%02de%02d", episode.getSeasonNumber(), episode.getEpisodeNumber()));

		List<Torrent> resultSet = search(searchSlugBuilder.toString());
		LOGGER.debug("LEAVING: search(); resultSet={}", resultSet);
		return resultSet;
	}

	@Override
	public List<Torrent> search(final String input) {
		// Resulting list
		List<Torrent> resultSet = new ArrayList<Torrent>();

		// Create search url
		String url = PIRATE_BAY_SEARCH_URL_TMPL.replace("{SEARCH_SLUG}", input);

		try {

			// Make request
			LOGGER.debug("Sending request: {}", url);
			Document resultPageDocument = Jsoup.connect(url)
					// Single line view
					// Easier to parse than double line; all data in columns
					.cookie("lw", "s").get();

			// Result table rows
			Elements resultRows = resultPageDocument.select("table#searchResult > tbody > tr");

			// Loop through all rows
			resultRows.forEach(new Consumer<Element>() {

				@Override
				public void accept(Element rowElement) {

					// Get type
					String type = rowElement.select("td.vertTh a").text();

					// Get name
					String name = rowElement.select("td:eq(1) > a").first().text();

					// Get magnet link
					String magnetLink = rowElement.select("td:eq(3) > nobr > a").first().attr("href");

					// Get info hash
					String infoHash = null;
					Pattern pattern = Pattern.compile(INFOHASH_REGEX_PATTERN);
					Matcher matcher = pattern.matcher(magnetLink);
					if (matcher.find()) {
						infoHash = matcher.group().toUpperCase();
					}

					// Get seeders
					int seeders = Integer.valueOf(rowElement.select("td:eq(5)").text());

					// Get leechers
					int leechers = Integer.valueOf(rowElement.select("td:eq(6)").text());

					// Get uploaded by
					String uploadedBy = rowElement.select("td:eq(7)").text();

					// Get uploaded
					String uploaded = rowElement.select("td:eq(2)").html().replace("&nbsp;", " ");
					Calendar uploadedCalendar = Calendar.getInstance();

					// Check for Y-day in the date
					if (uploaded.contains("Y-day")) {
						Calendar yesterday = Calendar.getInstance();
						yesterday.add(Calendar.DATE, -1);
						yesterday.set(Calendar.HOUR_OF_DAY, 0);
						yesterday.set(Calendar.MINUTE, 0);

						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd yyyy HH:mm");
						uploaded = simpleDateFormat.format(yesterday.getTime());
					}

					for (String supportedFormat : supportedDateFormats) {
						try {
							// Date format
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat(supportedFormat, Locale.ENGLISH);
							simpleDateFormat.setCalendar(uploadedCalendar);
							Date dateUploaded = simpleDateFormat.parse(uploaded);

							// Set calendar
							uploadedCalendar.setTime(dateUploaded);

							// When parsed properly - break
							break;

						} catch (ParseException pe) {
						}
					}

					if (uploadedCalendar.get(Calendar.YEAR) == 1970) {
						uploadedCalendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
					}

					// Get size
					String size = rowElement.select("td:eq(4)").html().replace("&nbsp;", " ");
					String[] sizeArray = size.split(" ");
					double sizeDouble = Double.parseDouble(sizeArray[0]);
					long byteSize = 0L;
					if (sizeArray[1].equals("MiB")) {
						byteSize = (long) (sizeDouble * 1048576);
					} else if (sizeArray[1].equals("GiB")) {
						byteSize = (long) (sizeDouble * 1073741824);
					}

					PirateBaySearchResultItem pirateBaySearchResultItem = new PirateBaySearchResultItem(type, name,
							uploadedCalendar, byteSize, uploadedBy, seeders, leechers, magnetLink, infoHash);

					resultSet.add(pirateBaySearchResultItem);

				}

			});

		} catch (IOException ioe) {
			LOGGER.warn("Failed to get search result: " + ioe.getMessage());
		}

		return resultSet;
	}

	@Override
	public String getName() {
		return "PirateBay.org";
	}

	@Override
	public String getDescription() {
		return "PirateBay.org Search Provider";
	}

	@Override
	public String getAuthor() {
		return "Bojan Bijelic";
	}

	@Override
	public String toString() {
		return getName();
	}
}
