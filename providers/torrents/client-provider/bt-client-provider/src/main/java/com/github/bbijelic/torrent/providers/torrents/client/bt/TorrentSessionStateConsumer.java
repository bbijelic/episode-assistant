package com.github.bbijelic.torrent.providers.torrents.client.bt;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.TorrentFinishedEvent;
import com.github.bbijelic.torrent.core.events.TorrentMetadataFetchedEvent;
import com.github.bbijelic.torrent.core.events.TorrentProgressEvent;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;

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

	@Override
	public void accept(TorrentSessionState state) {

		// Check for metadata flag and post event
		if (!isMetadataFetched && !client.getSession().getTorrent().getName().isEmpty()) {
			// Post event
			Events.getInstance().post(new TorrentMetadataFetchedEvent(torrent));
			isMetadataFetched = true;
		}

		// Torrent progress
		double progress = 1 - ((double) state.getPiecesRemaining() / (double) state.getPiecesTotal());
		LOGGER.info("Torrent progress: {} : {}, Remaining pieces: {}, Total pieces: {}", torrent.getName(), progress,
				state.getPiecesRemaining(), state.getPiecesTotal());

		// Emit torrent progress event
		Events.getInstance().post(new TorrentProgressEvent(progress, torrent));

		if (state.getPiecesRemaining() == 0) {
			LOGGER.debug("Download completed, stopping: {}", torrent.toString());

			// Post event
			Events.getInstance().post(new TorrentFinishedEvent(torrent));

			// Stop the client
			client.stop();
		}

	}

}
