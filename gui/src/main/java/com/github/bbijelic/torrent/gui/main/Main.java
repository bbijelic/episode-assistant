package com.github.bbijelic.torrent.gui.main;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	// Event bus
	private static EventBus eventBus = new EventBus();
	
	/**
	 * Event bus getter
	 * @return
	 */
	public synchronized static EventBus getEventBus() {
		return eventBus;
	}
		
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Root pane
	 */
	private static BorderPane rootPane = new BorderPane();
	
	/**
	 * Root pane getter
	 * 
	 * @return the root border pane
	 */
	public static BorderPane getRootPane() {
		return rootPane;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Menu bar
		URL menuBarUrl = getClass().getClassLoader().getResource("view/menubar.fxml");
		VBox menuBar = FXMLLoader.load(menuBarUrl);

		// Main pane
		URL mainPaneUrl = getClass().getClassLoader().getResource("view/episode-assistant.fxml");
		SplitPane mainPane = FXMLLoader.load(mainPaneUrl);
	
		rootPane.setTop(menuBar);
		rootPane.setCenter(mainPane);
		
		// Setting up the scene
		Scene scene = new Scene(rootPane, 1920, 1080);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Episode Assistant");
		primaryStage.setMaximized(true);
		primaryStage.show();

	}

}
