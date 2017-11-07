package com.github.bbijelic.torrent.core.episodes;

/**
 * Episode interface
 *
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public interface Episode {

	/**
	 * Show name getter
	 * 
	 * @return the show name
	 */
	String getShowName();
	
	/**
	 * Episode name getter
	 * 
	 * @return the episode name
	 */
	String getEpisodeName();
	
	/**
	 * Season number getter
	 * 
	 * @return the season number
	 */
	int getSeasonNumber();
	
	/**
	 * Episode number getter
	 * 
	 * @return the episode number within the season
	 */
	int getEpisodeNumber();
	
	/**
	 * Episode summary
	 * 
	 * @return the episode summary
	 */
	String getSummary();
	
	/**
	 * Episode search string
	 * 
	 * @return the episode search string
	 */
	String getSearchString();
	
}
