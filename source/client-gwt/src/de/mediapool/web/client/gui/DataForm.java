package de.mediapool.web.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.mediapool.web.client.dto.Movie;

public class DataForm extends FormPanel {

	Grid grid = new Grid(3, 2);
	TextBox textBox = new TextBox();
	Label textLabel = new Label();
	Button submit = new Button("Speichern");

	private Movie movie;

	public DataForm(Movie movie) {
		super();
		if (movie == null) {
			movie = new Movie();
			movie.setTitle("test");
		}

		setMovie(movie);

		refreshForm();

		grid.setWidget(0, 0, textLabel);
		grid.setWidget(0, 1, textBox);

		submit.addClickHandler(new MyHandler());
		grid.setWidget(2, 0, submit);

		setAction("/someAction");

		setEncoding(FormPanel.ENCODING_MULTIPART);
		setMethod(FormPanel.METHOD_POST);
		setWidget(grid);
		setStyleName("formPanel");

	}

	public void refreshForm() {
		textLabel.setText("Titel");
		textBox.setText(getMovie().getTitle());
	}

	public void refreshObject() {
		getMovie().setTitle(textBox.getText());
	}

	class MyHandler implements ClickHandler, KeyUpHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			refreshObject();
			Window.alert(getMovie().getTitle());
		}

		/**
		 * Fired when the user types in the nameField.
		 */
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

			}
		}

	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}