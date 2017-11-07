package com.github.bbijelic.torrent.gui.component.calendar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.FindTorrentEvent;
import com.github.bbijelic.torrent.core.events.SearchEpisodeEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Calendar component
 * 
 * @author Bojan Bijelic
 *
 */
public class CalendarComponent extends AnchorPane implements Initializable {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CalendarComponent.class);

	/**
	 * FXML path
	 */
	private static final String VIEW_FXML_PATH = "view/component/calendar/calendar.fxml";

	@FXML
	private TableView<EpisodeModel> calendarTableView;

	/**
	 * Observable list for the table view
	 */
	private ObservableList<EpisodeModel> itemsList = FXCollections.observableArrayList();
	
	/**
	 * Constructor
	 */
	public CalendarComponent() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(VIEW_FXML_PATH));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
	}

	@FXML
	private void onLoadCalendarBtnAction(ActionEvent e) {
		Thread worker = new Thread(new LoadCalendarWorker(itemsList));
		worker.setName(LoadCalendarWorker.class.getSimpleName());
		worker.start();

	}

	@FXML
	private void onClearCalendarBtn(ActionEvent e) {
		calendarTableView.getItems().clear();
	}

	@FXML
	private void onContextDownloadAction(ActionEvent e) {
		EpisodeModel episodeModel = calendarTableView.getSelectionModel().getSelectedItem();
		if (episodeModel != null) {
			// Publish download request event
			Events.getInstance().post(new FindTorrentEvent(episodeModel));
		}
	}
	
	@FXML
	private void onContextSearchAction(ActionEvent e) {
		EpisodeModel episodeModel = calendarTableView.getSelectionModel().getSelectedItem();
		if (episodeModel != null) {
			// Publish search request event
			Events.getInstance().post(new SearchEpisodeEvent(episodeModel));
		}
	}

	@FXML
	private void onContextMenuRemoveAction(ActionEvent e) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		calendarTableView.setItems(itemsList);
	}

}
