package com.github.bbijelic.torrent.gui.common.dialog;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Stack trace alert dialog
 * 
 * @author Bojan Bijelic
 *
 */
public class StackTraceAlert extends Alert {

	/**
	 * Constructor
	 * 
	 * @param alertType
	 */
	public StackTraceAlert(AlertType alertType, Throwable cause) {
		super(alertType);

		initialize(cause);
	}

	private void initialize(final Throwable cause) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		cause.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		getDialogPane().setExpandableContent(expContent);
	}

}
