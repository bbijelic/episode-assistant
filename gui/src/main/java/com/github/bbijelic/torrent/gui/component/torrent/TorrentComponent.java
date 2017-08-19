package com.github.bbijelic.torrent.gui.component.torrent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.gui.event.DownloadRequestEvent;
import com.github.bbijelic.torrent.gui.main.Main;
import com.google.common.eventbus.Subscribe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Torrent component
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentComponent extends AnchorPane implements Initializable {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TorrentComponent.class);

	/**
	 * FXML path
	 */
	private static final String VIEW_FXML_PATH = "view/component/torrent/torrent.fxml";

	@FXML
	private TableView<TorrentModel> torrentsTableView;

	/**
	 * Observable items list for the table view
	 */
	private ObservableList<TorrentModel> itemsList = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public TorrentComponent() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(VIEW_FXML_PATH));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();

		// Register to receive events from other components via Guava event bus
		Main.getEventBus().register(this);
	}

	@Subscribe
	private void handleDownloadRequestEvent(DownloadRequestEvent e) {
		LOGGER.debug("Download request: {}", e.toString());
		Thread worker = new Thread(new TorrentDownloadRequestWorker(itemsList, e.getEpisode()));
		worker.setName(TorrentDownloadRequestWorker.class.getSimpleName());
		worker.start();
	}

	@FXML
	private void onAddTorrentBtnAction(ActionEvent e) {

	}

	@FXML
	private void onStartTorrentBtnAction(ActionEvent e) {

	}

	@FXML
	private void onStopTorrentBtnAction(ActionEvent e) {

	}

	@FXML
	private void onRemoveTorrentBtnAction(ActionEvent e) {

	}

	@FXML
	private void onSearchTorrentBtnAction(ActionEvent e) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		torrentsTableView.setItems(itemsList);
	}
}
