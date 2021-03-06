/**
 * 
 */
package com.github.bbijelic.torrent.gui.component.torrent;

import java.util.Calendar;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.core.torrents.magnet.TorrentState;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Torrent Model
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentModel implements Torrent {

	private SimpleStringProperty name;

	@Override
	public String getName() {
		return name.get();
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	private SimpleStringProperty type;

	@Override
	public String getType() {
		return type.get();
	}

	public SimpleStringProperty typeProperty() {
		return type;
	}

	private Calendar uploadedCalendar;
	private SimpleStringProperty uploaded;

	@Override
	public Calendar getUploaded() {
		return uploadedCalendar;
	}

	public SimpleStringProperty uploadedProperty() {
		return uploaded;
	}

	private SimpleStringProperty size;

	@Override
	public long getSize() {
		return 0L;
	}

	public SimpleStringProperty sizeProperty() {
		return size;
	}

	private SimpleStringProperty uploadedBy;

	@Override
	public String getUploadedBy() {
		return uploadedBy.get();
	}

	public SimpleStringProperty uploadedByProperty() {
		return uploadedBy;
	}

	private SimpleIntegerProperty seeders;

	@Override
	public int getSeeders() {
		return seeders.get();
	}

	public SimpleIntegerProperty seedersProperty() {
		return seeders;
	}

	private SimpleIntegerProperty leechers;

	@Override
	public int getLeechers() {
		return leechers.get();
	}

	public SimpleIntegerProperty leechersProperty() {
		return leechers;
	}

	private SimpleStringProperty infoHash;

	@Override
	public String getInfoHash() {
		return infoHash.get();
	}

	public SimpleStringProperty infoHashProperty() {
		return infoHash;
	}

	private SimpleStringProperty magnetLink;

	@Override
	public String getMagnetLink() {
		return magnetLink.get();
	}

	public SimpleStringProperty magnetLinkProperty() {
		return magnetLink;
	}

	private Torrent torrent;

	public Torrent getTorrent() {
		return this.torrent;
	}

	private SimpleDoubleProperty progress;

	public SimpleDoubleProperty progressProperty() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress.set(progress);
	}

	private SimpleObjectProperty<TorrentState> torrentState;

	public void setTorrentState(TorrentState torrentState) {
		this.torrentState.set(torrentState);
	}

	public SimpleObjectProperty<TorrentState> torrentStateProperty() {
		return torrentState;
	}

	private SimpleDoubleProperty downloadSpeed;

	public void setDownloadSpeed(double downloadSpeed) {
		this.downloadSpeed.set(downloadSpeed);
	}

	public SimpleDoubleProperty downloadSpeedProperty() {
		return downloadSpeed;
	}

	private SimpleDoubleProperty uploadSpeed;

	public void setUploadSpeed(double uploadSpeed) {
		this.uploadSpeed.set(uploadSpeed);
	}

	public SimpleDoubleProperty uploadSpeedProperty() {
		return uploadSpeed;
	}

	private String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public TorrentModel(final Torrent torrent) {
		this.torrent = torrent;
		this.name = new SimpleStringProperty(torrent.getName());
		this.type = new SimpleStringProperty(torrent.getType());
		this.uploaded = new SimpleStringProperty(torrent.getUploaded().getTime().toString());
		this.size = new SimpleStringProperty(humanReadableByteCount(torrent.getSize(), true));
		this.uploadedBy = new SimpleStringProperty(torrent.getUploadedBy());
		this.seeders = new SimpleIntegerProperty(torrent.getSeeders());
		this.leechers = new SimpleIntegerProperty(torrent.getLeechers());
		this.infoHash = new SimpleStringProperty(torrent.getInfoHash());
		this.magnetLink = new SimpleStringProperty(torrent.getMagnetLink());
		this.progress = new SimpleDoubleProperty(0);
		this.torrentState = new SimpleObjectProperty<TorrentState>(TorrentState.FETCHING_METADATA);
		this.downloadSpeed = new SimpleDoubleProperty(0);
		this.uploadSpeed = new SimpleDoubleProperty(0);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TorrentModel [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", uploadedCalendar=");
		builder.append(uploadedCalendar);
		builder.append(", uploaded=");
		builder.append(uploaded);
		builder.append(", size=");
		builder.append(size);
		builder.append(", uploadedBy=");
		builder.append(uploadedBy);
		builder.append(", seeders=");
		builder.append(seeders);
		builder.append(", leechers=");
		builder.append(leechers);
		builder.append(", infoHash=");
		builder.append(infoHash);
		builder.append(", magnetLink=");
		builder.append(magnetLink);
		builder.append(", torrent=");
		builder.append(torrent);
		builder.append(", progress=");
		builder.append(progress);
		builder.append(", torrentState=");
		builder.append(torrentState);
		builder.append(", downloadSpeed=");
		builder.append(downloadSpeed);
		builder.append(", uploadSpeed=");
		builder.append(uploadSpeed);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((downloadSpeed == null) ? 0 : downloadSpeed.hashCode());
		result = prime * result + ((infoHash == null) ? 0 : infoHash.hashCode());
		result = prime * result + ((leechers == null) ? 0 : leechers.hashCode());
		result = prime * result + ((magnetLink == null) ? 0 : magnetLink.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((progress == null) ? 0 : progress.hashCode());
		result = prime * result + ((seeders == null) ? 0 : seeders.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((torrent == null) ? 0 : torrent.hashCode());
		result = prime * result + ((torrentState == null) ? 0 : torrentState.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uploadSpeed == null) ? 0 : uploadSpeed.hashCode());
		result = prime * result + ((uploaded == null) ? 0 : uploaded.hashCode());
		result = prime * result + ((uploadedBy == null) ? 0 : uploadedBy.hashCode());
		result = prime * result + ((uploadedCalendar == null) ? 0 : uploadedCalendar.hashCode());
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
		if (downloadSpeed == null) {
			if (other.downloadSpeed != null)
				return false;
		} else if (!downloadSpeed.equals(other.downloadSpeed))
			return false;
		if (infoHash == null) {
			if (other.infoHash != null)
				return false;
		} else if (!infoHash.equals(other.infoHash))
			return false;
		if (leechers == null) {
			if (other.leechers != null)
				return false;
		} else if (!leechers.equals(other.leechers))
			return false;
		if (magnetLink == null) {
			if (other.magnetLink != null)
				return false;
		} else if (!magnetLink.equals(other.magnetLink))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (progress == null) {
			if (other.progress != null)
				return false;
		} else if (!progress.equals(other.progress))
			return false;
		if (seeders == null) {
			if (other.seeders != null)
				return false;
		} else if (!seeders.equals(other.seeders))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (torrent == null) {
			if (other.torrent != null)
				return false;
		} else if (!torrent.equals(other.torrent))
			return false;
		if (torrentState == null) {
			if (other.torrentState != null)
				return false;
		} else if (!torrentState.equals(other.torrentState))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uploadSpeed == null) {
			if (other.uploadSpeed != null)
				return false;
		} else if (!uploadSpeed.equals(other.uploadSpeed))
			return false;
		if (uploaded == null) {
			if (other.uploaded != null)
				return false;
		} else if (!uploaded.equals(other.uploaded))
			return false;
		if (uploadedBy == null) {
			if (other.uploadedBy != null)
				return false;
		} else if (!uploadedBy.equals(other.uploadedBy))
			return false;
		if (uploadedCalendar == null) {
			if (other.uploadedCalendar != null)
				return false;
		} else if (!uploadedCalendar.equals(other.uploadedCalendar))
			return false;
		return true;
	}

}
