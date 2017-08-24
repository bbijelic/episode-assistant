package com.github.bbijelic.torrent.gui.component.torrent.search.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.gui.component.torrent.search.SearchTorrentController;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

/**
 * Search service running event handler
 * 
 * @author Bojan Bijelic
 *
 */
public class OnRunningEventHandler implements EventHandler<WorkerStateEvent> {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OnRunningEventHandler.class);

	/**
	 * Controller
	 */
	private SearchTorrentController controller;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            the controller
	 */
	public OnRunningEventHandler(final SearchTorrentController controller) {
		this.controller = controller;
	}

	@Override
	public void handle(WorkerStateEvent event) {
		LOGGER.debug("Showing progress indicator");
		
		// Set progress bar indicator visible
		controller.getProgressIndicator().setVisible(true);
	}

}
