package de.mediapool.web.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.mediapool.web.client.dto.Movie;
import de.mediapool.web.client.gui.DataForm;
import de.mediapool.web.client.gui.ImageForm;
import de.mediapool.web.client.gui.ListForm;
import de.mediapool.web.client.gui.MediaFormWidgets;
import de.mediapool.web.client.gui.SearchForm;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Media implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private static final String LIST_VIEW = "list_view";
	private static final String SEARCH_VIEW = "search_view";
	private static final String DATA_VIEW = "data_view";
	private static final String IMAGE_VIEW = "image_view";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final MediaServiceAsync mediaService = GWT.create(MediaService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Use RootPanel.get() to get the entire body element

		RootPanel.get().setSize("1024", "768");

		getMovieListForTable();

		getMfw().addWidget("ListForm", new ListForm());
		getMfw().addWidget("SearchForm", new SearchForm());
		getMfw().addWidget("DataForm", new DataForm());
		getMfw().addWidget("ImageForm", new ImageForm());

		RootPanel.get(LIST_VIEW).add(getMfw().getWidget("ListForm"));

		RootPanel.get(SEARCH_VIEW).add(getMfw().getWidget("SearchForm"));

		RootPanel.get(DATA_VIEW).add(getMfw().getWidget("DataForm"));

		RootPanel.get(IMAGE_VIEW).add(getMfw().getWidget("ImageForm"));

	}

	private void getMovieListForTable() {
		mediaService.getAllMovies(new AsyncCallback<List<Movie>>() {
			@Override
			public void onFailure(Throwable caught) {
				// getMfw().setMovieList(null);
			}

			@Override
			public void onSuccess(List<Movie> list) {
				getMfw().setMovieList(list);
				((ListForm) getMfw().getWidget("ListForm")).getMovieTable().fillMovieTable();
				getMfw().setSelectedMovie(list.get(0));
			}
		});
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}

}