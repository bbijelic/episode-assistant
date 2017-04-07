package com.github.bbijelic.torrent.sources.episodecalendar;

import java.io.InputStream;
import java.net.URL;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.source.Channel;
import com.github.bbijelic.torrent.core.source.Episode;
import com.github.bbijelic.torrent.core.source.EpisodeSource;
import com.github.bbijelic.torrent.core.source.Item;
import com.github.bbijelic.torrent.core.source.RSS;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Episode Calendar source
 * 
 * @author Bojan Bijelić
 */
public class EpisodeCalendarSource implements EpisodeSource {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EpisodeCalendarSource.class);
    
    /**
     * Episode feed url
     */
    private String url;
    
    /**
     * Constructor
     * 
     * @param url the episode feed url
     */
    public EpisodeCalendarSource(final String url){
        this.url = url;
    }
    
    @Override
    public Set<Episode> getEpisodes() throws Exception {
        
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("rss", RSS.class);
        xstream.alias("channel", Channel.class);
        xstream.alias("item", Item.class);
        xstream.alias("episode", Episode.class);
        
        xstream.alias("title", String.class);
        xstream.alias("description", String.class);
        xstream.alias("pubDate", String.class);
        xstream.alias("link", String.class);
        xstream.alias("guid", String.class);
                        
        xstream.ignoreUnknownElements();
        
        URL feedUrl = new URL(url);
        InputStream in = feedUrl.openStream();
        
        RSS rss = (RSS) xstream.fromXML(in);
                
        return rss.getChannel().getItem().get(0).getEpisodes();
    }
    
}
