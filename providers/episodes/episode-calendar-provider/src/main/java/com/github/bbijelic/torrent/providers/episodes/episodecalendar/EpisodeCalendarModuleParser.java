package com.github.bbijelic.torrent.providers.episodes.episodecalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleParser;

/**
 * Episode claendar module parser
 *
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public class EpisodeCalendarModuleParser implements ModuleParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(EpisodeCalendarModuleParser.class);
	
	public static final String ITEM_PUB_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";
    
    private SimpleDateFormat simpleDateFormat;
    
    public EpisodeCalendarModuleParser(){
        this.simpleDateFormat = new SimpleDateFormat(ITEM_PUB_DATE_FORMAT, Locale.ENGLISH);
    }
	
	@Override
	public String getNamespaceUri() {
		return "";
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Module parse(Element rootElement) {
		
		EpisodeCalendarDayModuleImpl dayModule = new EpisodeCalendarDayModuleImpl();
		Calendar date = Calendar.getInstance();
        try {
            date.setTime(simpleDateFormat.parse(rootElement.getChildText("pubDate")));
        } catch(ParseException pe){
            LOGGER.warn(pe.getMessage());
        }
        dayModule.setDate(date);
        
        Element episodesNode = rootElement.getChild("episodes");
        List episodes = (List) episodesNode.getChildren("episode");
        for(Object episodeObj : episodes){
            Element episodeElement = (Element)episodeObj;
            
            EpisodeCalendarEpisodeModuleImpl episode = new EpisodeCalendarEpisodeModuleImpl();
            episode.setShowName(episodeElement.getChildText("show"));
            episode.setEpisodeName(episodeElement.getChildText("name"));
            episode.setSeasonNumber(Integer.parseInt(episodeElement.getChildTextNormalize("season_number")));
            episode.setEpisodeNumber(Integer.parseInt(episodeElement.getChildTextNormalize("episode_number")));
            episode.setSummary(episodeElement.getChildTextNormalize("summary"));
            
            dayModule.getEpisodes().add(episode);
        }
        
        return dayModule;
	}

}
