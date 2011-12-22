package de.mediapool.web.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.CellPreviewEvent;

import de.mediapool.web.client.dto.Movie;
import de.mediapool.web.client.gui.MovieListTable;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Media implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final MediaServiceAsync mediaService = GWT.create(MediaService.class);

	List<Movie> movieList;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Use RootPanel.get() to get the entire body element

		RootPanel.get().setSize("1024", "768");

		MovieListTable movieTable = new MovieListTable(getMovieList());
		RootPanel.get("list_view").add(movieTable);

		getMoviesForTable(movieTable);

		final Button sendButton = new Button("Send");
		RootPanel.get("search_view").add(sendButton);
		sendButton.setText("Suche");

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		final TextBox nameField = new TextBox();
		RootPanel.get("search_view").add(nameField);
		nameField.setSize("149px", "24px");

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		VerticalPanel verticalPanel = new VerticalPanel();
		RootPanel.get("data_view").add(verticalPanel, 214, 235);
		verticalPanel.setSize("168px", "26px");

		Label lblNewLabel = new Label("New label");
		verticalPanel.add(lblNewLabel);

		final Label lblNewLabel_1 = new Label("New label");
		verticalPanel.add(lblNewLabel_1);
		lblNewLabel_1.setSize("167px", "16px");

		Image image = new Image("test");
		RootPanel.get("image_view").add(image, 214, 89);
		image.setSize("168px", "140px");

		movieTable.addCellPreviewHandler(new CellPreviewEvent.Handler<Movie>() {
			public void onCellPreview(CellPreviewEvent<Movie> event) {
				String type = event.getNativeEvent().getType();
				if (type.equals("click")) {
					refreshDataForm(lblNewLabel_1, event.getValue());
				}
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
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

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		nameField.addKeyUpHandler(handler);
		sendButton.addClickHandler(handler);
	}

	private void refreshDataForm(Label lblNewLabel_1, Movie movie) {
		lblNewLabel_1.setText(movie.getTitle());
	}

	private void getMoviesForTable(MovieListTable table) {
		final MovieListTable movieTable = table;
		mediaService.getAllMovies(new AsyncCallback<List<Movie>>() {
			@Override
			public void onFailure(Throwable caught) {
				setMovieList(null);
			}

			@Override
			public void onSuccess(List<Movie> list) {
				setMovieList(list);
				movieTable.setMovieList(list);
				movieTable.fillMovieTable();
			}
		});
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

}
