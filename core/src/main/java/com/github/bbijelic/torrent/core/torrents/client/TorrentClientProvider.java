package com.github.bbijelic.torrent.core.torrents.client;

import com.github.bbijelic.torrent.core.provider.Provider;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

/**
 * Torrent client interface
 * 
 * @author Bojan Bijelic
 *
 */
public interface TorrentClientProvider extends Provider {

	/**
	 * Starts torrent download
	 * 
	 * @param torrent
	 *            the torrent
	 * @throws TorrentClientException
	 */
	public void startDownload(Torrent torrent) throws TorrentClientException;

	/**
	 * Stops torrent download
	 * 
	 * @param torrent
	 * @throws TorrentClientException
	 */
	public void stopDownload(Torrent torrent) throws TorrentClientException;

}
