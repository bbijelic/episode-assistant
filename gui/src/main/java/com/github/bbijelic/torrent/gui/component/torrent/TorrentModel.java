/**
 * 
 */
package com.github.bbijelic.torrent.gui.component.torrent;

import com.github.bbijelic.torrent.core.episodes.Episode;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Torrent Model
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentModel implements Episode {

	private final SimpleStringProperty showName;

	@Override
	public String getShowName() {
		return showName.get();
	}

	public void setShowName(String showName) {
		this.showName.set(showName);
	}

	public SimpleStringProperty showNameProperty() {
		return showName;
	}

	private SimpleStringProperty episodeName;

	@Override
	public String getEpisodeName() {
		return episodeName.get();
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName.set(episodeName);
	}

	public SimpleStringProperty episodeNameProperty() {
		return episodeName;
	}

	private SimpleIntegerProperty seasonNumber;

	@Override
	public int getSeasonNumber() {
		return seasonNumber.get();
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber.set(seasonNumber);
	}

	public SimpleIntegerProperty seasonNumberProperty() {
		return seasonNumber;
	}

	private SimpleIntegerProperty episodeNumber;

	@Override
	public int getEpisodeNumber() {
		return episodeNumber.get();
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber.set(episodeNumber);
	}

	public SimpleIntegerProperty episodeNumberProperty() {
		return episodeNumber;
	}

	private SimpleStringProperty summary;

	@Override
	public String getSummary() {
		return summary.get();
	}

	public void setSummary(String summary) {
		this.summary.set(summary);
	}

	public SimpleStringProperty summaryProperty() {
		return summary;
	}
	
	private SimpleStringProperty torrentName;
	
	public String getTorrentName() {
		return torrentName.get();
	}
	
	public void setTorrentName(String torrentName) {
		this.torrentName.set(torrentName);
	}
	
	public SimpleStringProperty torrentNameProperty() {
		return torrentName;
	}

	public TorrentModel(String showName, String episodeName, int seasonNumber, int episodeNumber, String summary, String torrentName) {
		super();
		this.showName = new SimpleStringProperty(showName);
		this.episodeName = new SimpleStringProperty(episodeName);
		this.seasonNumber = new SimpleIntegerProperty(seasonNumber);
		this.episodeNumber = new SimpleIntegerProperty(episodeNumber);
		this.summary = new SimpleStringProperty(summary);
		this.torrentName = new SimpleStringProperty(torrentName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TorrentModel [showName=");
		builder.append(showName);
		builder.append(", episodeName=");
		builder.append(episodeName);
		builder.append(", seasonNumber=");
		builder.append(seasonNumber);
		builder.append(", episodeNumber=");
		builder.append(episodeNumber);
		builder.append(", summary=");
		builder.append(summary);
		builder.append(", torrentName=");
		builder.append(torrentName);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((episodeName == null) ? 0 : episodeName.hashCode());
		result = prime * result + ((episodeNumber == null) ? 0 : episodeNumber.hashCode());
		result = prime * result + ((seasonNumber == null) ? 0 : seasonNumber.hashCode());
		result = prime * result + ((showName == null) ? 0 : showName.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((torrentName == null) ? 0 : torrentName.hashCode());
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
		TorrentModel other = (TorrentModel) obj;
		if (episodeName == null) {
			if (other.episodeName != null)
				return false;
		} else if (!episodeName.equals(other.episodeName))
			return false;
		if (episodeNumber == null) {
			if (other.episodeNumber != null)
				return false;
		} else if (!episodeNumber.equals(other.episodeNumber))
			return false;
		if (seasonNumber == null) {
			if (other.seasonNumber != null)
				return false;
		} else if (!seasonNumber.equals(other.seasonNumber))
			return false;
		if (showName == null) {
			if (other.showName != null)
				return false;
		} else if (!showName.equals(other.showName))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (torrentName == null) {
			if (other.torrentName != null)
				return false;
		} else if (!torrentName.equals(other.torrentName))
			return false;
		return true;
	}

}
