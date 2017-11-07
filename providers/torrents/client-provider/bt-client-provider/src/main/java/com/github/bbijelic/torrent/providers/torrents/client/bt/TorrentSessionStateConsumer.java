package com.github.bbijelic.torrent.providers.torrents.client.bt;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.TorrentFinishedEvent;
import com.github.bbijelic.torrent.core.events.TorrentMetadataFetchedEvent;
import com.github.bbijelic.torrent.core.events.TorrentProgressEvent;
import com.github.bbijelic.torrent.core.events.TorrentSpeedEvent;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.core.torrents.magnet.TorrentSpeed;

import bt.runtime.BtClient;
import bt.torrent.TorrentSessionState;

/**
 * Torrent session state consumer
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentSessionStateConsumer implements Consumer<TorrentSessionState> {

	/**
	 * Logger
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(TorrentSessionStateConsumer.class);

	/**
	 * Torrent
	 */
	private Torrent torrent;

	/**
	 * Client
	 */
	private BtClient client;

	private boolean isMetadataFetched = false;

	/**
	 * Constructor
	 * 
	 * @param torrent
	 */
	public TorrentSessionStateConsumer(final Torrent torrent, final BtClient client) {
		this.torrent = torrent;
		this.client = client;
	}

	/**
	 * Last tick time
	 */
	private long lastTickTimeMillis = 0;
	private long lastDownloadIndex = 0;
	private long lastUploadIndex = 0;

	@Override
	public void accept(TorrentSessionState state) {

		// Calculate speeds
		calculateSpeed(state);

		// Metadata downloaded?
		checkMetadata();
		// Torrent progress
		checkProgress(state);
		// Is torrent finished
		checkIsFinished(state);
	}

	/**
	 * Calculates download/upload speed and posts an event
	 * 
	 * @param state
	 * @param timeDelta
	 */
	private void calculateSpeed(final TorrentSessionState state) {
		final long timeNowNanoTime = System.currentTimeMillis();
		final double timeDelta = (timeNowNanoTime - lastTickTimeMillis) / 1000;

		if (timeDelta > 0) {
			// Calulate download and upload speeds
			final double downloadSpeed = calculateDownloadSpeed(state, timeDelta);
			final double uploadSpeed = caluclateUploadSpeed(state, timeDelta);

			// Torrent speed
			TorrentSpeed torrentSpeed = new TorrentSpeedInfo(downloadSpeed, uploadSpeed);
			
			// Post event
			Events.getInstance().post(new TorrentSpeedEvent(torrent, torrentSpeed));
		}

		lastTickTimeMillis = timeNowNanoTime;
	}

	/**
	 * Calculates download speed of the torrent
	 * 
	 * @param state
	 *            the torrent session state
	 * @param timeDelta
	 *            the time delta
	 */
	private double calculateDownloadSpeed(final TorrentSessionState state, final double timeDelta) {
		final long currentDownloadIndex = state.getDownloaded();
		final double downloadDelta = (currentDownloadIndex - lastDownloadIndex) / 1000;
		final double downloadSpeed = downloadDelta / timeDelta;
		lastDownloadIndex = currentDownloadIndex;
		return downloadSpeed;
	}

	/**
	 * Calculates upload speed of the torrent
	 * 
	 * @param state
	 *            the torrent session state
	 * @param timeDelta
	 *            the time delta
	 */
	private double caluclateUploadSpeed(final TorrentSessionState state, final double timeDelta) {
		final long currentUploadIndex = state.getUploaded();
		final double uploadDelta = (currentUploadIndex - lastUploadIndex) / 1000;
		final double uploadSpeed = uploadDelta / timeDelta;
		lastUploadIndex = currentUploadIndex;
		return uploadSpeed;
	}

	/**
	 * Checks if torrent download is finished. This occurs when remaining pieces to
	 * download is 0
	 * 
	 * @param state
	 *            the torrent session state
	 */
	private void checkIsFinished(final TorrentSessionState state) {
		if (state.getPiecesRemaining() == 0) {
			LOGGER.debug("Download completed, stopping: {}", torrent.toString());

			// Post event
			Events.getInstance().post(new TorrentFinishedEvent(torrent));

			// Stop the client
			client.stop();
		}
	}

	/**
	 * Checks progress of the torrent download session
	 * 
	 * @param state
	 *            the torrent session state
	 */
	private void checkProgress(final TorrentSessionState state) {
		double progress = 1 - ((double) state.getPiecesRemaining() / (double) state.getPiecesTotal());
		LOGGER.info("Torrent progress: {} : {}, Remaining pieces: {}, Total pieces: {}", torrent.getName(), progress,
				state.getPiecesRemaining(), state.getPiecesTotal());

		// Emit torrent progress event
		Events.getInstance().post(new TorrentProgressEvent(progress, torrent));
	}

	/**
	 * Checks if torrent has all metadata
	 */
	private void checkMetadata() {
		
		
		// Check for metadata flag and post event
		if (!isMetadataFetched && !torrent.getName().isEmpty()) {
			// Post event
			Events.getInstance().post(new TorrentMetadataFetchedEvent(torrent));
			isMetadataFetched = true;
		}
	}

}
