package com.github.bbijelic.torrent.gui.component.torrent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.FindTorrentEvent;
import com.github.bbijelic.torrent.core.events.SearchEpisodeEvent;
import com.github.bbijelic.torrent.core.events.SelectedTorrentEvent;
import com.github.bbijelic.torrent.core.events.StartDownloadTorrentEvent;
import com.github.bbijelic.torrent.core.events.StopDownloadTorrentEvent;
import com.github.bbijelic.torrent.core.events.TorrentFinishedEvent;
import com.github.bbijelic.torrent.core.events.TorrentMetadataFetchedEvent;
import com.github.bbijelic.torrent.core.events.TorrentProgressEvent;
import com.github.bbijelic.torrent.core.events.TorrentSpeedEvent;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.core.torrents.magnet.TorrentState;
import com.github.bbijelic.torrent.gui.component.torrent.search.SearchTorrentController;
import com.google.common.eventbus.Subscribe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
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

	@FXML
	private TableColumn<TorrentModel, Double> progressColumn;

	@FXML
	private Button startTorrentBtn;

	/**
	 * Start torrent button getter
	 * 
	 * @return the start torrent button getter
	 */
	public Button getStartTorrentBtn() {
		return startTorrentBtn;
	}

	@FXML
	private Button stopTorrentBtn;

	/**
	 * Stop torrent button getter
	 * 
	 * @return the torrent stop button getter
	 */
	public Button getStopTorrentBtn() {
		return stopTorrentBtn;
	}

	@FXML
	private Button removeTorrentBtn;

	/**
	 * Remove torrent button getter
	 * 
	 * @return the remove torrent button getter
	 */
	public Button getRemoveTorrentBtn() {
		return removeTorrentBtn;
	}

	/**
	 * Constructor
	 */
	public TorrentComponent() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(VIEW_FXML_PATH));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();

		// Register to receive events from other components via Guava event bus
		Events.getInstance().register(this);
	}

	@Subscribe
	private void handleTorrentProgressEvent(TorrentProgressEvent e) {
		itemsList.forEach(new Consumer<TorrentModel>() {
			@Override
			public void accept(TorrentModel torrentModel) {
				if (torrentModel.getInfoHash().equalsIgnoreCase(e.getTorrent().getInfoHash())) {
					torrentModel.setProgress(e.getProgress());
				}
			}
		});
	}
	
	@Subscribe
	private void handleSearchEpisodeEvent(SearchEpisodeEvent e) {
		LOGGER.debug("Search request: {}", e.toString());
		SearchTorrentController.showDialog(e.getEpisode().getSearchString());
	}
	
	@Subscribe
	private void handleDownloadRequestEvent(FindTorrentEvent e) {
		LOGGER.debug("Download request: {}", e.toString());
		Thread worker = new Thread(new TorrentDownloadRequestWorker(e.getEpisode()));
		worker.setName(TorrentDownloadRequestWorker.class.getSimpleName());
		worker.start();
	}

	@Subscribe
	private void handleStartDownloadTorrentEvent(StartDownloadTorrentEvent e) {
		TorrentModel torrentModel = new TorrentModel(e.getTorrent());
		// Adding torrent to the observable table view list
		if (!itemsList.contains(torrentModel))
			itemsList.add(torrentModel);
	}

	@Subscribe
	private void handleTorrentMetadataFetchedEvent(TorrentMetadataFetchedEvent e) {
		updateTorrentState(e.getTorrent(), TorrentState.DOWNLOADING);
	}

	@Subscribe
	private void handleSelectedTorrentEvent(SelectedTorrentEvent e) {
		LOGGER.debug("Handling SelectedTorrentEvent event: {}", e.toString());
		startTorrentBtn.setDisable(false);
		stopTorrentBtn.setDisable(false);
		removeTorrentBtn.setDisable(false);
	}

	@Subscribe
	private void handleTorrentFinishedEvent(TorrentFinishedEvent e) {
		LOGGER.debug("Handling TorrentFinishedEvent event: {}", e.toString());
		// Update torrent state
		updateTorrentState(e.getTorrent(), TorrentState.FINISHED);
	}

	@Subscribe
	private void handleTorrentSpeedEvent(TorrentSpeedEvent e) {
		LOGGER.debug("Handling TorrentSpeedEvent event: {}", e.toString());
		// Torrent metadata fetched
		itemsList.forEach(new Consumer<TorrentModel>() {
			@Override
			public void accept(TorrentModel torrentModel) {
				if (torrentModel.getInfoHash().equalsIgnoreCase(e.getTorrent().getInfoHash())) {
					torrentModel.setDownloadSpeed(e.getTorrentSpeed().getDownloadSpeed());
					torrentModel.setUploadSpeed(e.getTorrentSpeed().getUploadSpeed());
				}
			}
		});
	}

	private void updateTorrentState(final Torrent torrent, final TorrentState state) {
		// Torrent metadata fetched
		itemsList.forEach(new Consumer<TorrentModel>() {
			@Override
			public void accept(TorrentModel torrentModel) {
				if (torrentModel.getInfoHash().equalsIgnoreCase(torrent.getInfoHash())) {
					torrentModel.setTorrentState(state);
				}
			}
		});
	}

	@FXML
	private void onSearchTorrentBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onSearchTorrentBtnAction event: {}", e.toString());
		SearchTorrentController.showDialog(null);
	}

	@FXML
	private void onStartTorrentBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onStartTorrentBtnAction event: {}", e.toString());
		// Get selected torrent
		TorrentModel torrentModel = torrentsTableView.getSelectionModel().getSelectedItem();
		if (torrentModel != null) {
			LOGGER.debug("Starting torrent download: {}", torrentModel.getTorrent().toString());
			// Update torrent state
			updateTorrentState(torrentModel.getTorrent(), TorrentState.FETCHING_METADATA);
			// Post stop download event
			Events.getInstance().post(new StartDownloadTorrentEvent(torrentModel.getTorrent()));
		}
	}

	@FXML
	private void onStopTorrentBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onStopTorrentBtnAction event: {}", e.toString());
		// Get selected torrent
		TorrentModel torrentModel = torrentsTableView.getSelectionModel().getSelectedItem();
		if (torrentModel != null) {
			LOGGER.debug("Stopping torrent download: {}", torrentModel.getTorrent().toString());
			// Update torrent state
			updateTorrentState(torrentModel.getTorrent(), TorrentState.STOPPED);
			// Post stop download event
			Events.getInstance().post(new StopDownloadTorrentEvent(torrentModel.getTorrent()));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressColumn.setCellValueFactory(new PropertyValueFactory<TorrentModel, Double>("progress"));
		progressColumn.setCellFactory(ProgressBarTableCell.<TorrentModel>forTableColumn());
		torrentsTableView.setItems(itemsList);

		// Set selected item change listener
		torrentsTableView.getSelectionModel().selectedItemProperty().addListener(new SelectedItemChangeListener());
	}
}
