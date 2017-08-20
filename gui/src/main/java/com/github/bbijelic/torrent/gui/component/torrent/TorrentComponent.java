package com.github.bbijelic.torrent.gui.component.torrent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.FindTorrentEvent;
import com.github.bbijelic.torrent.core.events.TorrentProgressEvent;
import com.github.bbijelic.torrent.gui.component.torrent.search.SearchTorrentController;
import com.google.common.eventbus.Subscribe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
	private void handleDownloadRequestEvent(FindTorrentEvent e) {
		LOGGER.debug("Download request: {}", e.toString());
		Thread worker = new Thread(new TorrentDownloadRequestWorker(itemsList, e.getEpisode()));
		worker.setName(TorrentDownloadRequestWorker.class.getSimpleName());
		worker.start();
	}

	@FXML
	private void onSearchTorrentBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onSearchTorrentBtnAction event: {}", e.toString());
		SearchTorrentController.showDialog();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressColumn.setCellValueFactory(new PropertyValueFactory<TorrentModel, Double>("progress"));
		progressColumn.setCellFactory(ProgressBarTableCell.<TorrentModel>forTableColumn());
		torrentsTableView.setItems(itemsList);
	}
}
