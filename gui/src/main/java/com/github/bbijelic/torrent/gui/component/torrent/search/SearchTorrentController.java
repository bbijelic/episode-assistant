package com.github.bbijelic.torrent.gui.component.torrent.search;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.StartDownloadTorrentEvent;
import com.github.bbijelic.torrent.core.torrents.magnet.SearchProvider;
import com.github.bbijelic.torrent.gui.component.torrent.TorrentModel;
import com.github.bbijelic.torrent.gui.component.torrent.search.service.TorrentSearchService;
import com.github.bbijelic.torrent.gui.component.torrent.search.service.handler.OnFailedEventHandler;
import com.github.bbijelic.torrent.gui.component.torrent.search.service.handler.OnRunningEventHandler;
import com.github.bbijelic.torrent.gui.component.torrent.search.service.handler.OnSuccessEventHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Search torrent controller
 * 
 * @author Bojan Bijelic
 *
 */
public class SearchTorrentController implements Initializable {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchTorrentController.class);

	/**
	 * FXML path for the 'search torrent' dialog
	 */
	private static final String VIEW_FXML = "view/component/torrent/search/search.fxml";

	@FXML
	private void onAddTorrentBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onAddTorrentBtnAction event: {}", e.toString());
	}

	/**
	 * Shows dialog
	 */
	public static void showDialog(final String searchInput) {
		try {

			// Load 'search torrent' fxml
			FXMLLoader fxmlLoader = new FXMLLoader(
					SearchTorrentController.class.getClassLoader().getResource(VIEW_FXML));

			// Stage
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.DECORATED);
			stage.setTitle("Search Torrent...");
			stage.setScene(new Scene(fxmlLoader.load()));
			stage.show();

			if (searchInput != null) {
				SearchTorrentController controller = (SearchTorrentController) fxmlLoader.getController();
				controller.getSearchTxt().setText(searchInput);
				controller.startSearch(searchInput);
			}
			
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	@FXML
	private Button searchBtn;

	/**
	 * Search button getter
	 * 
	 * @return the search button
	 */
	public Button getSearchBtn() {
		return searchBtn;
	}

	@FXML
	private ProgressIndicator progressIndicator;

	/**
	 * Progress indicator getter
	 * 
	 * @return the progress indicator
	 */
	public ProgressIndicator getProgressIndicator() {
		return progressIndicator;
	}

	/**
	 * Result table observable list
	 */
	private ObservableList<TorrentModel> resultList = FXCollections.observableArrayList();

	/**
	 * Observable result list getter
	 * 
	 * @return the observable result list
	 */
	public ObservableList<TorrentModel> getResultList() {
		return resultList;
	}

	public void startSearch(final String searchInput) {
		// Get search provider
		SearchProvider searchProvider = searchProviderChoice.getValue();

		// Execute search on a new thread
		TorrentSearchService searchService = new TorrentSearchService(searchInput, searchProvider);
		searchService.setOnRunning(new OnRunningEventHandler(this));
		searchService.setOnSucceeded(new OnSuccessEventHandler(this));
		searchService.setOnFailed(new OnFailedEventHandler(this));

		// Start service
		searchService.start();
	}

	@FXML
	private void onSearchBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onSearchBtnAction event: {}", e.toString());

		// Get search text
		String searchInput = searchTxt.getText();
		startSearch(searchInput);
	}

	@FXML
	private void onDownloadBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onDownloadBtnAction event: {}", e.toString());

		// Get selected torrent
		TorrentModel torrentModel = resultsTableView.getSelectionModel().getSelectedItem();
		if (torrentModel != null) {
			// Post torrent download request event
			Events.getInstance().post(new StartDownloadTorrentEvent(torrentModel.getTorrent()));
			LOGGER.debug("Torrent download requested: {}", torrentModel.getTorrent().toString());
		}
	}

	@FXML
	private TextField searchTxt;

	/**
	 * Search text getter
	 * 
	 * @return the search text
	 */
	public TextField getSearchTxt() {
		return searchTxt;
	}

	@FXML
	private TableView<TorrentModel> resultsTableView;

	/**
	 * Result table view getter
	 * 
	 * @return the result table view
	 */
	public TableView<TorrentModel> getResultsTableView() {
		return resultsTableView;
	}

	@FXML
	private ChoiceBox<SearchProvider> searchProviderChoice;

	/**
	 * Search provider choice getter
	 * 
	 * @return the search provider choice
	 */
	public ChoiceBox<SearchProvider> getSearchProviderChoice() {
		return searchProviderChoice;
	}

	private ObservableList<SearchProvider> searchProviderItems;

	/**
	 * Search provider observable list getter
	 * 
	 * @return the search provider observable list getter
	 */
	public ObservableList<SearchProvider> getSearchProviderItems() {
		return searchProviderItems;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOGGER.debug("Initializing controller: {}", this.getClass().getName());

		// Load available providers
		searchProviderItems = FXCollections.observableArrayList();
		// Instance of service loader for the search providers
		ServiceLoader<SearchProvider> searchProviders = ServiceLoader.load(SearchProvider.class);
		searchProviders.forEach(new Consumer<SearchProvider>() {

			@Override
			public void accept(SearchProvider searchProvider) {
				searchProviderItems.add(searchProvider);
			}
		});

		searchProviderChoice.setItems(searchProviderItems);
		searchProviderChoice.getSelectionModel().selectFirst();

		// Set result list to the table view
		resultsTableView.setItems(resultList);
	}
}
