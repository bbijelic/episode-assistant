package com.github.bbijelic.torrent.core.torrents.magnet;

/**
 * Torrent Speed
 * 
 * @author Bojan Bijelic
 *
 */
public interface TorrentSpeed {

	/**
	 * Returns download speed in kB/s
	 * 
	 * @return the download speed in kB/s
	 */
	double getDownloadSpeed();

	/**
	 * Returns upload speed in kB/s
	 * 
	 * @return the upload speed in kB/s
	 */
	double getUploadSpeed();

}
