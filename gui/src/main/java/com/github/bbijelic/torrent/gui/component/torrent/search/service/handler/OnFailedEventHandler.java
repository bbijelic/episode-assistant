package com.github.bbijelic.torrent.gui.component.torrent.search.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.gui.common.dialog.StackTraceAlert;
import com.github.bbijelic.torrent.gui.component.torrent.search.SearchTorrentController;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;

/**
 * On Failed event handler
 * 
 * @author Bojan Bijelic
 *
 */
public class OnFailedEventHandler implements EventHandler<WorkerStateEvent> {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OnFailedEventHandler.class);

	/**
	 * Controller
	 */
	private SearchTorrentController controller;

	/**
	 * Controller
	 * 
	 * @param controller
	 */
	public OnFailedEventHandler(final SearchTorrentController controller) {
		this.controller = controller;
	}

	@Override
	public void handle(WorkerStateEvent event) {
		LOGGER.debug("Handling torrent service failed search request: {}", event.toString());

		// Hide the progress indicator
		controller.getProgressIndicator().setVisible(false);

		// Get exception caused the fail
		Throwable cause = event.getSource().getException();

		// Initialite stack trace alert and show
		StackTraceAlert alert = new StackTraceAlert(AlertType.ERROR, cause);
		alert.setHeaderText("Search request failed. Check details below to see more information.");
		alert.setContentText(cause.getMessage());
		alert.showAndWait();

	}

}
