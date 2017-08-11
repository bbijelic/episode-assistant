package com.github.bbijelic.torrent.gui.main.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EpisodeModel implements com.github.bbijelic.torrent.core.episodes.Episode {

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

	private SimpleStringProperty releaseDate;

	public String getReleaseDate() {
		return releaseDate.get();
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate.set(releaseDate);
	}
	
	public SimpleStringProperty releaseDateProperty() {
		return releaseDate;
	}

	private SimpleObjectProperty<EpisodeStaus> episodeStaus;

	public EpisodeStaus getEpisodeStaus() {
		return episodeStaus.get();
	}

	public void setEpisodeStaus(EpisodeStaus episodeStaus) {
		this.episodeStaus.set(episodeStaus);
	}
	
	public SimpleObjectProperty<EpisodeStaus> episodeStausProperty() {
		return episodeStaus;
	}

	private SimpleDoubleProperty progress;

	public double getProgress() {
		return progress.get();
	}

	public void setProgress(double progress) {
		this.progress.set(progress);
	}
	
	public SimpleDoubleProperty progressProperty() {
		return progress;
	}

	public EpisodeModel(String showName, String episodeName, int seasonNumber, int episodeNumber, String summary,
			Calendar releaseDate, EpisodeStaus episodeStaus, double progress) {
		super();
		this.showName = new SimpleStringProperty(showName);
		this.episodeName = new SimpleStringProperty(episodeName);
		this.seasonNumber = new SimpleIntegerProperty(seasonNumber);
		this.episodeNumber = new SimpleIntegerProperty(episodeNumber);
		this.summary = new SimpleStringProperty(summary);
		this.episodeStaus = new SimpleObjectProperty<EpisodeStaus>(episodeStaus);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.releaseDate = new SimpleStringProperty(sdf.format(releaseDate.getTime()));

		this.progress = new SimpleDoubleProperty(progress);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EpisodeModel [showName=");
		builder.append(showName);
		builder.append(", episodeName=");
		builder.append(episodeName);
		builder.append(", seasonNumber=");
		builder.append(seasonNumber);
		builder.append(", episodeNumber=");
		builder.append(episodeNumber);
		builder.append(", summary=");
		builder.append(summary);
		builder.append(", releaseDate=");
		builder.append(releaseDate);
		builder.append(", episodeStaus=");
		builder.append(episodeStaus);
		builder.append(", progress=");
		builder.append(progress);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((episodeName == null) ? 0 : episodeName.hashCode());
		result = prime * result + episodeNumber.get();
		result = prime * result + ((episodeStaus == null) ? 0 : episodeStaus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(progress.get());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + seasonNumber.get();
		result = prime * result + ((showName == null) ? 0 : showName.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
		EpisodeModel other = (EpisodeModel) obj;
		if (episodeName == null) {
			if (other.episodeName != null)
				return false;
		} else if (!episodeName.equals(other.episodeName))
			return false;
		if (episodeNumber != other.episodeNumber)
			return false;
		if (episodeStaus != other.episodeStaus)
			return false;
		if (Double.doubleToLongBits(progress.get()) != Double.doubleToLongBits(other.progress.get()))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (seasonNumber != other.seasonNumber)
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
		return true;
	}

}
