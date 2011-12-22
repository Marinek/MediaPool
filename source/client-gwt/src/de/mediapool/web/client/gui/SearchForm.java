package de.mediapool.web.client.gui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.mediapool.web.client.MediaServiceAsync;
import de.mediapool.web.client.dto.Movie;

public class SearchForm extends FormPanel {

	MediaServiceAsync mediaService;
	List<Movie> movieList;

	public SearchForm(MediaServiceAsync mediaService, List<Movie> movieList) {

		this.mediaService = mediaService;
		setMovieList(movieList);
		Button sendButton = new Button("Send");
		RootPanel.get("search_view").add(sendButton);
		sendButton.setText("Suche");

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		final TextBox nameField = new TextBox();
		this.add(nameField);
		nameField.setSize("149px", "24px");

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		MyHandler handler = new MyHandler();
		nameField.addKeyUpHandler(handler);
		sendButton.addClickHandler(handler);
	}

	// Create a handler for the sendButton and nameField
	class MyHandler implements ClickHandler, KeyUpHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			searchForMovies();
		}

		/**
		 * Fired when the user types in the nameField.
		 */
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				searchForMovies();
			}
		}

		/**
		 * Send the name from the nameField to the server and wait for a
		 * response.
		 */
		private void searchForMovies() {
			mediaService.getAllMovies(new AsyncCallback<List<Movie>>() {
				public void onFailure(Throwable caught) {
					setMovieList(null);
				}

				@Override
				public void onSuccess(List<Movie> list) {
					setMovieList(list);
				}
			});

		}
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	// Add a handler to send the name to the server

}
