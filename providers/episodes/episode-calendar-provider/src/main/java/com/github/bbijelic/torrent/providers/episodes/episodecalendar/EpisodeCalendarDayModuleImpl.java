package com.github.bbijelic.torrent.providers.episodes.episodecalendar;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.sun.syndication.feed.module.ModuleImpl;

/**
 * Episode calendar day
 *
 * @author Bojan Bijelić
 * @since 1.0.0
 */
public class EpisodeCalendarDayModuleImpl extends ModuleImpl implements EpisodeCalendarDayModule {

	private static final long serialVersionUID = 3606041461662295496L;

	public EpisodeCalendarDayModuleImpl() {
		super(EpisodeCalendarDayModule.class, "");
	}

	private Calendar date;

	@Override
	public Calendar getDate() {
		return this.date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	private Set<Episode> episodes = new HashSet<Episode>();

	@Override
	public Set<Episode> getEpisodes() {
		return this.episodes;
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}

	@Override
	public void copyFrom(Object obj) {
		EpisodeCalendarDayModule other = (EpisodeCalendarDayModule) obj;
		this.date = other.getDate();
		this.episodes = other.getEpisodes();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Class getInterface() {
		return EpisodeCalendarDayModule.class;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EpisodeCalendarDayModuleImpl [date=").append(date).append(", episodes=").append(episodes)
				.append("]");
		return builder.toString();
	}

}
