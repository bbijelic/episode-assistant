package com.github.bbijelic.torrent;

import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.github.bbijelic.torrent.config.ServiceConfiguration;
import com.github.bbijelic.torrent.core.episodes.EpisodesProvider;
import com.github.bbijelic.torrent.core.torrents.file.TorrentFileProvider;
import com.github.bbijelic.torrent.core.torrents.infohash.InfoHashProvider;

public class Service extends Application<ServiceConfiguration> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);
    
    @Override
    public String getName() {
        return "torrent-assistant";
    }
    
    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }
    
    @Override
    public void run(ServiceConfiguration config, Environment env) throws Exception {
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
        
        // Instance of service loader for the epsisode providers
        ServiceLoader<TorrentFileProvider> torrentProviderServiceLoader = ServiceLoader.load(TorrentFileProvider.class);        
        torrentProviderServiceLoader.forEach(new Consumer<TorrentFileProvider>() {
            @Override
            public void accept(TorrentFileProvider provider) {
                LOGGER.info("Loaded torrent file provider: {}", provider.getName());                
            }
        });
    }
    
    public static void main(String[] args) throws Exception {
        new Service().run(new String[]{"server", System.getProperty("service.config")});
    }
    
}
