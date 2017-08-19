package com.github.bbijelic.torrent.gui.main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.episodes.Episode;
import com.github.bbijelic.torrent.core.episodes.EpisodeBatch;
import com.github.bbijelic.torrent.core.episodes.EpisodeProviderException;
import com.github.bbijelic.torrent.core.episodes.EpisodesProvider;
import com.github.bbijelic.torrent.gui.component.calendar.EpisodeModel;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.PirateBaySearch;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.PirateBaySearchResultItem;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.KeywordComparator;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.MultiComparator;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.PopularityComparator;
import com.github.bbijelic.torrent.providers.torrents.infohash.piratebay.sort.SizeComparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@FXML
	private TableView<EpisodeModel> episodesTable;

	@FXML
	private TableColumn<EpisodeModel, String> showNameColumn;

	@FXML
	private TableColumn<EpisodeModel, String> episodeNameColumn;

	@FXML
	private TableColumn<EpisodeModel, Integer> seasonNumberColumn;

	@FXML
	private TableColumn<EpisodeModel, Integer> episodeNumberColumn;

	@FXML
	private TableColumn<EpisodeModel, String> releaseDateColumn;

	@FXML
	private Button loadEpisodesBtn;

	@FXML
	private void loadEpisodesAction(ActionEvent e) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				
				// Instance of service loader for the epsisode providers
				ServiceLoader<EpisodesProvider> episodeProviderServiceLoader = ServiceLoader
						.load(EpisodesProvider.class);
				episodeProviderServiceLoader.forEach(new Consumer<EpisodesProvider>() {

					@Override
					public void accept(EpisodesProvider provider) {
						LOGGER.info("Loaded episode provider: {}", provider.getName());

						Properties config = new Properties();
						config.setProperty("rsslink",
								"https://episodecalendar.com/en/rss_feed/bojan.bijelic.os@gmail.com");

						try {

							ObservableList<EpisodeModel> dataList = FXCollections.observableArrayList();
							for (EpisodeBatch episodeBatch : provider.getEpisodes(config)) {
								for (Episode episode : episodeBatch.getEpisodes()) {

									dataList.add(new EpisodeModel(episode.getShowName(), episode.getEpisodeName(),
											episode.getSeasonNumber(), episode.getEpisodeNumber(), episode.getSummary(),
											episodeBatch.getDate()));
								}
							}

							showNameColumn.setCellValueFactory(new PropertyValueFactory<>("showName"));
							episodeNameColumn.setCellValueFactory(new PropertyValueFactory<>("episodeName"));
							seasonNumberColumn.setCellValueFactory(new PropertyValueFactory<>("seasonNumber"));
							episodeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("episodeNumber"));
							releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

							episodesTable.setItems(dataList);

							// Download menu item
							MenuItem downloadMenuItem = new MenuItem("Download...");
							downloadMenuItem.getStyleClass().add("context-menu-download");
							downloadMenuItem.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									EpisodeModel episodeModel = episodesTable.getSelectionModel().getSelectedItem();
									if (episodeModel != null)
										downloadEpisode(episodeModel);
								}
							});

							// Pause downloading menu item
							MenuItem pauseDownloadingMenuItem = new MenuItem("Pause Downloading...");
							pauseDownloadingMenuItem.setDisable(true);
							pauseDownloadingMenuItem.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									EpisodeModel episodeModel = episodesTable.getSelectionModel().getSelectedItem();
									if (episodeModel != null)
										pauseDownloading(episodeModel);
								}
							});

							// Stop downloading menu item
							MenuItem stopDownloadingMenuItem = new MenuItem("Stop Downloading...");
							stopDownloadingMenuItem.setDisable(true);
							stopDownloadingMenuItem.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									EpisodeModel episodeModel = episodesTable.getSelectionModel().getSelectedItem();
									if (episodeModel != null)
										stopDownloading(episodeModel);
								}
							});

							// Remove episode menu item
							MenuItem removeEpisodeMenuItem = new MenuItem("Remove Episode...");
							removeEpisodeMenuItem.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									EpisodeModel episodeModel = episodesTable.getSelectionModel().getSelectedItem();
									if (episodeModel != null)
										removeEpisode(episodeModel);

								}
							});

							ContextMenu contextMenu = new ContextMenu();
							contextMenu.getItems().addAll(downloadMenuItem, pauseDownloadingMenuItem,
									stopDownloadingMenuItem, new SeparatorMenuItem(), removeEpisodeMenuItem);

							episodesTable.setContextMenu(contextMenu);

						} catch (EpisodeProviderException e) {
							LOGGER.error(e.getMessage(), e);
						}
					}
				});

			}
		}).start();

	}

	private void downloadEpisode(final EpisodeModel episodeModel) {
		LOGGER.debug("Downloading: {}", episodeModel.toString());

		new Thread(new Runnable() {

			@Override
			public void run() {
				
				// Keywords priority
				Map<String, Integer> keywordsPriority = new HashMap<String, Integer>();
				keywordsPriority.put("KILLERS", 2);
				keywordsPriority.put("FUM", 2);
				keywordsPriority.put("MTB", 2);
				keywordsPriority.put("DEFLATE", 1);
				keywordsPriority.put("LOL", 3);
				keywordsPriority.put("FLEET", 1);
				keywordsPriority.put("DIMENSION", 1);
				keywordsPriority.put("[ettv]", 2);
				keywordsPriority.put("x264", 3);
				keywordsPriority.put("x265", -10);
				keywordsPriority.put("HEVC", -10);

				// Quality priority
				Map<String, Integer> qualityPriority = new HashMap<String, Integer>();
				qualityPriority.put("HDTV", 3);
				qualityPriority.put("WEBRIP", 2);
				qualityPriority.put("WEB-RIP", 2);
				qualityPriority.put("WEBDL", 2);
				qualityPriority.put("WEB DL", 2);
				qualityPriority.put("WEB-DL", 2);

				// Pirate bay search instance
				PirateBaySearch pirateBaySearch = new PirateBaySearch();
				List<PirateBaySearchResultItem> searchResults = pirateBaySearch.search(episodeModel);

				if (searchResults.isEmpty()) {
									
					LOGGER.info("Episode not found: {}", episodeModel.toString());
					return;
				}

				// List of comparators
				List<Comparator<PirateBaySearchResultItem>> comparatorList = new ArrayList<Comparator<PirateBaySearchResultItem>>();

				comparatorList.add(new KeywordComparator(keywordsPriority));
				comparatorList.add(new KeywordComparator(qualityPriority));
				comparatorList.add(new PopularityComparator());
				comparatorList.add(new SizeComparator());

				// Multi comparator instance
				MultiComparator<PirateBaySearchResultItem> multiComparator = new MultiComparator<PirateBaySearchResultItem>(
						comparatorList);

				Collections.sort(searchResults, multiComparator);

				PirateBaySearchResultItem resultItem = searchResults.get(0);
				if (resultItem != null) {					
					LOGGER.debug(resultItem.toString());
				}

			}
		}).start();

	}

	private void pauseDownloading(final EpisodeModel episodeModel) {
		LOGGER.debug("Pausing download: {}", episodeModel.toString());
	}

	private void stopDownloading(final EpisodeModel episodeModel) {
		LOGGER.debug("Stopping download: {}", episodeModel.toString());
	}

	private void removeEpisode(final EpisodeModel episodeModel) {
		LOGGER.debug("Removing episode: {}", episodeModel.toString());
		episodesTable.getItems().remove(episodeModel);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
