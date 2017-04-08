package com.github.bbijelic.torrent.core.episodes;

import java.util.Calendar;
import java.util.Set;

/**
 * Episode batch. Usualy represents calendar day.
 *
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public interface EpisodeBatch {

	/**
	 * Date of the batch
	 * @return
	 */
	Calendar getDate();
	
	/**
	 * Returns set of episode
	 * @return the episodes set
	 */
	Set<Episode> getEpisodes();
	
}
