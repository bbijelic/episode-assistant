package com.github.bbijelic.torrent;

import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.EpisodesProvider;
import com.github.bbijelic.torrent.core.torrents.infohash.InfoHashProvider;

public class Service {

	private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

	public static void main(String[] args) throws Exception {
		
		// Instance of service loader for the epsisode providers
		ServiceLoader<EpisodesProvider> episodeProviderServiceLoader = ServiceLoader.load(EpisodesProvider.class);
		episodeProviderServiceLoader.forEach(new Consumer<EpisodesProvider>() {
			@Override
			public void accept(EpisodesProvider provider) {
				LOGGER.info("Loaded episode provider: {}", provider.getName());
			}
		});

		// Instance of service loader for the info hash providers
		ServiceLoader<InfoHashProvider> infoHashProviderServiceLoader = ServiceLoader.load(InfoHashProvider.class);
		infoHashProviderServiceLoader.forEach(new Consumer<InfoHashProvider>() {
			@Override
			public void accept(InfoHashProvider provider) {
				LOGGER.info("Loaded torrent infohash provider: {}", provider.getName());
			}
		});
	
		

	}

}
