package com.github.bbijelic.torrent.providers.torrents.magnet.piratebay;

import com.github.bbijelic.torrent.core.episodes.Episode;

public class EpisodeTestImpl implements Episode {
    
    public EpisodeTestImpl(final String showName,
        final String episodeName, final int seasonNumber, 
        final int episodeNumber, final String summary) {

        this.showName = showName;
        this.episodeName = episodeName;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.summary = summary;
        
    }
    
    private String episodeName;
    
    @Override
    public String getEpisodeName() {
        return episodeName;
    }
    
    private int episodeNumber;
    
    @Override
    public int getEpisodeNumber() {
        return episodeNumber;
    }
    
    private int seasonNumber;
    
    @Override
    public int getSeasonNumber() {
        return seasonNumber;
    }
    
    private String showName;
    
    @Override
    public String getShowName() {
        return showName;
    }
    
    private String summary;
    
    @Override
    public String getSummary() {
        return summary;
    }
        
    
}
