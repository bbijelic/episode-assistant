package com.github.bbijelic.torrent.sources.episodecalendar;

import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import com.github.bbijelic.torrent.core.source.Episode;

public class EpisodeCalendarSourceTest {
    
    @Test
    public void testSource(){
        
        try {
            
            String feedUrl = "https://episodecalendar.com/en/rss_feed/bojan.bijelic.os@gmail.com";
            EpisodeCalendarSource episodeCalendarSource = new EpisodeCalendarSource(feedUrl);
            Set<Episode> episodes = episodeCalendarSource.getEpisodes();
            
        } catch(Exception e){
            fail(e.getMessage());            
        }
        
        
    }
    
}
