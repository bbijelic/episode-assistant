package com.github.bbijelic.torrent.gui.main;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

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
		URL mainPaneUrl = getClass().getClassLoader().getResource("view/main.fxml");
		AnchorPane mainPane = FXMLLoader.load(mainPaneUrl);
		
		rootPane.setTop(menuBar);
		rootPane.setCenter(mainPane);
		
		// Setting up the scene
		Scene scene = new Scene(rootPane, 1024, 786);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
