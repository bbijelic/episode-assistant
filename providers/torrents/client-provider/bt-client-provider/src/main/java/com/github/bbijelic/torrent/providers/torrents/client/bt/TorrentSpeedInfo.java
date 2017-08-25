package com.github.bbijelic.torrent.providers.torrents.client.bt;

import com.github.bbijelic.torrent.core.torrents.magnet.TorrentSpeed;

/**
 * Torrent speed info
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentSpeedInfo implements TorrentSpeed {

	private double uploadSpeed = 0;

	private double downloadSpeed = 0;

	/**
	 * Constructor
	 * 
	 * @param downloadSpeed
	 *            the download speed
	 * @param uploadSpeed
	 *            the upload speed
	 */
	public TorrentSpeedInfo(final double downloadSpeed, final double uploadSpeed) {
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
	}

	@Override
	public double getDownloadSpeed() {
		return downloadSpeed;
	}

	@Override
	public double getUploadSpeed() {
		return uploadSpeed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TorrentSpeedInfo [uploadSpeed=");
		builder.append(uploadSpeed);
		builder.append(", downloadSpeed=");
		builder.append(downloadSpeed);
		builder.append("]");
		return builder.toString();
	}

}
