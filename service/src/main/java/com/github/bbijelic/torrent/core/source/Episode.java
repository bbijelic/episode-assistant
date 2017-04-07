package com.github.bbijelic.torrent.core.source;

import java.util.Calendar;
import java.util.Objects;

/**
 * Episode
 * 
 * @author Bojan Bijelić
 */
public class Episode {
    
    /**
     * TV Show name
     */
    private String showName;
    
    /**
     * Show name getter
     * 
     * @return the show name
     */
    public String getShowName() {
        return showName;
    }
    
    /**
     * Show name setter
     * 
     * @param showName the show name
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }
    
    /**
     * Episode name
     */
    private String episodeName;
    
    /**
     * Episode name getter
     * 
     * @return the episode name
     */
    public String getEpisodeName() {
        return episodeName;
    }
    
    /**
     * Episode name setter
     * 
     * @param episodeName the episode name
     */
    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }
    
    /**
     * Season number
     */
    private int season;
    
    /**
     * Season number getter
     * 
     * @return the season number
     */
    public int getSeason() {
        return season;
    }
    
    /**
     * Season number setter
     * 
     * @param season the season number
     */
    public void setSeason(int season) {
        this.season = season;
    }
    
    /**
     * Episode number
     */
    private int episode;
    
    /**
     * Episode getter
     * 
     * @return the episode number
     */
    public int getEpisode() {
        return episode;
    }
    
    /**
     * Episode setter
     * 
     * @param episode the episode number
     */
    public void setEpisode(int episode) {
        this.episode = episode;
    }
    
    /**
     * Air date
     */
    private Calendar airDate;
    
    /**
     * Air date getter
     * 
     * @return the air date
     */
    public Calendar getAirDate() {
        return airDate;
    }
    
    /**
     * Air date setter
     * 
     * @param airDate the air date
     */
    public void setAirDate(Calendar airDate) {
        this.airDate = airDate;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(showName, episodeName, season, episode, airDate);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Episode){
            Episode other = (Episode)obj;
            if(this.showName.equalsIgnoreCase(other.getShowName())
                && this.episodeName.equalsIgnoreCase(other.getEpisodeName())
                && this.season == other.getSeason()
                && this.episode == other.getEpisode() )
                return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Episode [")
            .append("showName=").append(showName)
            .append(", episodeName=").append(episodeName)
            .append(", season=").append(season)
            .append(", episode=").append(episode)
            .append(", airDate=").append(airDate.getTime())
            .append("]");
        return builder.toString();
    }
    
}
