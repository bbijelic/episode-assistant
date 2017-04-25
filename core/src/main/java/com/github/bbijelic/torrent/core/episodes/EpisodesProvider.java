package com.github.bbijelic.torrent.core.episodes;

import java.util.Properties;
import java.util.Set;

import com.github.bbijelic.torrent.core.provider.Provider;

/**
 * Episodes provider interface
 * 
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public interface EpisodesProvider extends Provider {
	
	/**
	 * Episode provider method
	 * 
	 * @param config the provider configuration
	 * @return the set of episode batches
	 * @throws EpisodeProviderException
	 */
	Set<EpisodeBatch> getEpisodes(Properties config) throws EpisodeProviderException;
	
}
