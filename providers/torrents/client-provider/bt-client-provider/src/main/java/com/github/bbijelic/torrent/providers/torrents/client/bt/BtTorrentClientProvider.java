/**
 * 
 */
package com.github.bbijelic.torrent.providers.torrents.client.bt;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.StartDownloadTorrentEvent;
import com.github.bbijelic.torrent.core.events.StopDownloadTorrentEvent;
import com.github.bbijelic.torrent.core.torrents.client.TorrentClientException;
import com.github.bbijelic.torrent.core.torrents.client.TorrentClientProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Module;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.BtRuntime;
import bt.runtime.Config;

/**
 * Bt torrent client provider
 * 
 * @author Bojan Bijelic
 *
 */
public class BtTorrentClientProvider implements TorrentClientProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(BtTorrentClientProvider.class);

	@Override
	public String getName() {
		return "Bt Torrent Client Provider";
	}

	@Override
	public String getDescription() {
		return "Bt torrent client provider";
	}

	@Override
	public String getAuthor() {
		return "Bojan Bijelic";
	}

	/**
	 * Bt runtime
	 */
	private BtRuntime btRuntime;

	/**
	 * Bt runtime config
	 */
	private Config btRuntimeConfig;

	/**
	 * Bt DHT module
	 */
	private Module btDhtModule;

	/**
	 * Storage
	 */
	private Storage storage;

	/**
	 * Constructor
	 */
	public BtTorrentClientProvider() {

		// Enable multithreaded verification of torrent data
		// TODO Load configuration from database
		btRuntimeConfig = new Config() {

			@Override
			public int getNumOfHashingThreads() {
				return Runtime.getRuntime().availableProcessors() * 2;
			}

			@Override
			public int getMaxConcurrentlyActivePeerConnectionsPerTorrent() {
				return 20;
			}

			@Override
			public int getMaxPeerConnections() {
				return 100;
			}

		};

		// enable bootstrapping from public routers
		btDhtModule = new DHTModule(new DHTConfig() {
			@Override
			public boolean shouldUseRouterBootstrap() {
				return true;
			}

		});

		// Storage
		// TODO Get from database
		storage = new FileSystemStorage(new File("C:\\Users\\Snijele\\Desktop\\TEST").toPath());

		// Initialize Bt runtime
		btRuntime = BtRuntime.builder(btRuntimeConfig).module(btDhtModule).autoLoadModules().disableAutomaticShutdown()
				.build();

		// Register to the even bus
		Events.getInstance().register(this);
	}

	private Map<String, BtClient> clientMap = new HashMap<>();

	/**
	 * Handles StartDownloadTorrentEvent event
	 * 
	 * @param event
	 */
	@Subscribe
	public void startDownloadTorrentEventHandler(StartDownloadTorrentEvent event) {
		try {

			// Start download
			startDownload(event.getTorrent());

		} catch (TorrentClientException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void stopDownloadTorrentEventHandler(StopDownloadTorrentEvent event) {
		try {

			// Stop download
			stopDownload(event.getTorrent());

		} catch (TorrentClientException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void startDownload(Torrent torrent) throws TorrentClientException {
		// Client
		BtClient client = Bt.client(btRuntime).storage(storage).magnet(torrent.getMagnetLink()).build();
		clientMap.put(torrent.getInfoHash(), client);
		client.startAsync(new TorrentSessionStateConsumer(torrent, client), 1000);
	}

	@Override
	public void stopDownload(Torrent torrent) throws TorrentClientException {
		if (clientMap.containsKey(torrent.getInfoHash())) {
			clientMap.get(torrent.getInfoHash()).stop();
			clientMap.remove(torrent.getInfoHash());
		}
	}

}
