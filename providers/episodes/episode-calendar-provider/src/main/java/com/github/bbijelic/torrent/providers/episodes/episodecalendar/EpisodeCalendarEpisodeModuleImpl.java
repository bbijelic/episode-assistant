package com.github.bbijelic.torrent.providers.episodes.episodecalendar;

import com.sun.syndication.feed.module.ModuleImpl;

/**
 * Episode calendar episode module
 *
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public class EpisodeCalendarEpisodeModuleImpl extends ModuleImpl implements EpisodeCalendarEpisodeModule {

	private static final long serialVersionUID = -4020183481903191230L;

	public EpisodeCalendarEpisodeModuleImpl() {
		super(EpisodeCalendarEpisodeModule.class, "");
	}

	@Override
	public void copyFrom(Object obj) {
		EpisodeCalendarEpisodeModule other = (EpisodeCalendarEpisodeModule) obj;
		this.showName = other.getShowName();
		this.episodeName = other.getEpisodeName();
		this.seasonNumber = other.getSeasonNumber();
		this.episodeNumber = other.getEpisodeNumber();
		this.summary = other.getSummary();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Class getInterface() {
		return EpisodeCalendarEpisodeModule.class;
	}

	private String showName;

	@Override
	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	private String episodeName;

	@Override
	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	private int seasonNumber;

	@Override
	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	private int episodeNumber;

	@Override
	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	private String summary;

	@Override
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EpisodeCalendarEpisodeModuleImpl [showName=").append(showName).append(", episodeName=")
				.append(episodeName).append(", seasonNumber=").append(seasonNumber).append(", episodeNumber=")
				.append(episodeNumber).append(", summary=").append(summary).append("]");
		return builder.toString();
	}

}
