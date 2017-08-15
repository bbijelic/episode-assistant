package com.github.bbijelic.torrent.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Config torrent entity
 *
 * @author Bojan Bijelic
 */
@Entity
@Table(name = "config_torrent")
public class ConfigTorrent {

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
	 * Download output directory
	 */
	@Column(name = "output_directory", insertable = true, updatable = true, nullable = false)
	private String outputDirectory;

	/**
	 * Output directory getter
	 * 
	 * @return the output directory
	 */
	public String getOutputDirectory() {
		return outputDirectory;
	}

	/**
	 * Output directory setter
	 * 
	 * @param outputDirectory
	 *            the output directory
	 */
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	@Override
	public String toString() {
		return "ConfigTorrent [id=" + id + ", outputDirectory=" + outputDirectory + ", maxPeerConnections="
				+ maxPeerConnections + ", maxPeerConnectionsPerTorrent=" + maxPeerConnectionsPerTorrent + "]";
	}

	/**
	 * Max peer connections
	 */
	@Column(name = "max_peer_connections", insertable = true, updatable = true, nullable = false)
	private int maxPeerConnections;

	/**
	 * Max peer connections getter
	 * 
	 * @return the max peer connections
	 */
	public int getMaxPeerConnections() {
		return maxPeerConnections;
	}

	/**
	 * Max peer connections setter
	 * 
	 * @param maxPeerConnections
	 *            the max peer connections
	 */
	public void setMaxPeerConnections(int maxPeerConnections) {
		this.maxPeerConnections = maxPeerConnections;
	}

	/**
	 * Max peer connections per torrent
	 */
	@Column(name = "max_peer_connections_per_torrent", insertable = true, updatable = true, nullable = false)
	private int maxPeerConnectionsPerTorrent;

	/**
	 * Max peer connections per torrent getter
	 * 
	 * @return the max peer connections per torrent
	 */
	public int getMaxPeerConnectionsPerTorrent() {
		return maxPeerConnectionsPerTorrent;
	}

	/**
	 * Max peer connections per torrent setter
	 * 
	 * @param maxPeerConnectionsPerTorrent
	 *            the max peer connections per torrent
	 */
	public void setMaxPeerConnectionsPerTorrent(int maxPeerConnectionsPerTorrent) {
		this.maxPeerConnectionsPerTorrent = maxPeerConnectionsPerTorrent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + maxPeerConnections;
		result = prime * result + maxPeerConnectionsPerTorrent;
		result = prime * result + ((outputDirectory == null) ? 0 : outputDirectory.hashCode());
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
		ConfigTorrent other = (ConfigTorrent) obj;
		if (id != other.id)
			return false;
		if (maxPeerConnections != other.maxPeerConnections)
			return false;
		if (maxPeerConnectionsPerTorrent != other.maxPeerConnectionsPerTorrent)
			return false;
		if (outputDirectory == null) {
			if (other.outputDirectory != null)
				return false;
		} else if (!outputDirectory.equals(other.outputDirectory))
			return false;
		return true;
	}

}
