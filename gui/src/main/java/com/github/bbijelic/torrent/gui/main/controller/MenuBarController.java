package com.github.bbijelic.torrent.gui.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Menu bar controller
 * 
 * @author Bojan Bijelic <bojan.bijelic.os@gmail.com>
 *
 */
public class MenuBarController implements Initializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuBarController.class);

	@FXML
	private MenuBar menuBar;

	@FXML
	private void handleAboutAction(final ActionEvent event) {
		provideAboutFunctionality();
	}

	/**
	 * Perform functionality associated with "About" menu selection or CTRL-A.
	 */
	private void provideAboutFunctionality() {
		LOGGER.info("About clicked");
	}

	/**
	 * Handle action related to input (in this case specifically only responds to
	 * keyboard event CTRL-A).
	 * 
	 * @param event
	 *            Input event.
	 */
	@FXML
	private void handleKeyInput(final InputEvent event) {
		if (event instanceof KeyEvent) {
			final KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
				provideAboutFunctionality();
			}
		}
	}

	public void initialize(URL location, ResourceBundle resources) {
		menuBar.setFocusTraversable(true);
	}

}
