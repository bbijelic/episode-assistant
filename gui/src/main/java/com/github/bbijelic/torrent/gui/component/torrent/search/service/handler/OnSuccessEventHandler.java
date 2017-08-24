package com.github.bbijelic.torrent.gui.component.torrent.search.service.handler;

import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.gui.component.torrent.TorrentModel;
import com.github.bbijelic.torrent.gui.component.torrent.search.SearchTorrentController;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class OnSuccessEventHandler implements EventHandler<WorkerStateEvent> {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OnSuccessEventHandler.class);

	/**
	 * Controller
	 */
	private SearchTorrentController controller;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public OnSuccessEventHandler(final SearchTorrentController controller) {
		this.controller = controller;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void handle(WorkerStateEvent event) {
		// Get result list from the event
		List<Torrent> searchResultList = (List<Torrent>) event.getSource().getValue();

		// Clear the table view items list before adding
		controller.getResultList().clear();

		LOGGER.debug("Adding search result items to the results table view: {}", searchResultList.toString());

		// Iterate over all search result items and add to table view items
		searchResultList.forEach(new Consumer<Torrent>() {

			@Override
			public void accept(Torrent torrent) {
				controller.getResultList().add(new TorrentModel(torrent));
			}

		});

		LOGGER.debug("Hidding progress indicator");
		// Set progress bar indicator hidden
		controller.getProgressIndicator().setVisible(false);
	}

}
