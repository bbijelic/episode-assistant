package com.github.bbijelic.torrent.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Torrent entity
 * 
 * @author Bojan Bijelic
 *
 */
@Entity
@Table(name = "torrent", uniqueConstraints = { @UniqueConstraint(columnNames = { "show_name", "episode_name", "season",
		"episode" }, name = "torrent_showname_episode_name_season_episode_uq") })
public class Torrent {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/**
	 * ID getter
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Id setter
	 * 
	 * @param id
	 *            the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Show name
	 */
	@Column(name = "show_name", insertable = true, updatable = true, nullable = false)
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
	 * @param showName
	 *            the show name
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * Episode name
	 */
	@Column(name = "episode_name", insertable = true, updatable = true, nullable = false)
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
	 * Episode neme setter
	 * 
	 * @param episodeName
	 *            the episode name
	 */
	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	/**
	 * Season
	 */
	@Column(name = "season", insertable = true, updatable = true, nullable = false)
	private int season;

	/**
	 * Season getter
	 * 
	 * @return the season
	 */
	public int getSeason() {
		return season;
	}

	/**
	 * Season setter
	 * 
	 * @param season
	 *            the season
	 */
	public void setSeason(int season) {
		this.season = season;
	}

	/**
	 * Episode
	 */
	@Column(name = "episode", insertable = true, updatable = true, nullable = false)
	private int episode;

	/**
	 * Episode getter
	 * 
	 * @return the episode
	 */
	public int getEpisode() {
		return episode;
	}

	/**
	 * Episode setter
	 * 
	 * @param episode
	 *            the epsisode
	 */
	public void setEpisode(int episode) {
		this.episode = episode;
	}

	/**
	 * Magnet link
	 */
	@Column(name = "magnet_link", insertable = true, updatable = true, nullable = false)
	private String magnetLink;

	/**
	 * Magnet link getter
	 * 
	 * @return the magnet link
	 */
	public String getMagnetLink() {
		return magnetLink;
	}

	/**
	 * Magnet link setter
	 * 
	 * @param magnetLink
	 *            the magnet link
	 */
	public void setMagnetLink(String magnetLink) {
		this.magnetLink = magnetLink;
	}

	/**
	 * Torrent state
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "state", insertable = true, updatable = true, nullable = false)
	private TorrentState state;

	/**
	 * Torrent state getter
	 * 
	 * @return the torrent state
	 */
	public TorrentState getState() {
		return state;
	}

	/**
	 * Torrent state setter
	 * 
	 * @param state
	 */
	public void setState(TorrentState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Torrent [id=");
		builder.append(id);
		builder.append(", showName=");
		builder.append(showName);
		builder.append(", episodeName=");
		builder.append(episodeName);
		builder.append(", season=");
		builder.append(season);
		builder.append(", episode=");
		builder.append(episode);
		builder.append(", magnetLink=");
		builder.append(magnetLink);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + episode;
		result = prime * result + ((episodeName == null) ? 0 : episodeName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((magnetLink == null) ? 0 : magnetLink.hashCode());
		result = prime * result + season;
		result = prime * result + ((showName == null) ? 0 : showName.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torrent other = (Torrent) obj;
		if (episode != other.episode)
			return false;
		if (episodeName == null) {
			if (other.episodeName != null)
				return false;
		} else if (!episodeName.equals(other.episodeName))
			return false;
		if (id != other.id)
			return false;
		if (magnetLink == null) {
			if (other.magnetLink != null)
				return false;
		} else if (!magnetLink.equals(other.magnetLink))
			return false;
		if (season != other.season)
			return false;
		if (showName == null) {
			if (other.showName != null)
				return false;
		} else if (!showName.equals(other.showName))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

}
