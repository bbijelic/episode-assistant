package com.github.bbijelic.torrent.gui.component.torrent.search;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.torrents.magnet.SearchProvider;
import com.github.bbijelic.torrent.core.torrents.magnet.Torrent;
import com.github.bbijelic.torrent.gui.component.torrent.TorrentModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	public static void showDialog() {
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

		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	@FXML
	private Button searchBtn;

	/**
	 * Result table observable list
	 */
	private ObservableList<TorrentModel> resultList = FXCollections.observableArrayList();

	@FXML
	private void onSearchBtnAction(ActionEvent e) {
		LOGGER.debug("Handling onSearchBtnAction event: {}", e.toString());

		// Get search text
		String searchInput = searchTxt.getText();
		// Get search provider
		SearchProvider searchProvider = searchProviderChoice.getValue();

		// Execute search on a new thread
		TorrentSearchService searchService = new TorrentSearchService(searchInput, searchProvider);
		searchService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			@SuppressWarnings("unchecked")
			public void handle(WorkerStateEvent event) {

				// Get result list from the event
				List<Torrent> searchResultList = (List<Torrent>) event.getSource().getValue();

				// Clear the table view items list before adding
				resultList.clear();

				// Iterate over all search result items and add to table view items
				searchResultList.forEach(new Consumer<Torrent>() {

					@Override
					public void accept(Torrent torrent) {
						resultList.add(new TorrentModel(torrent));
					}

				});

			}
		});

		// Start service
		searchService.start();
	}

	@FXML
	private TextField searchTxt;

	@FXML
	private TableView<TorrentModel> resultsTableView;

	@FXML
	private ChoiceBox<SearchProvider> searchProviderChoice;

	private ObservableList<SearchProvider> searchProviderItems;

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
