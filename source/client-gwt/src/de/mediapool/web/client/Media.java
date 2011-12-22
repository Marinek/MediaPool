package de.mediapool.web.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.CellPreviewEvent;

import de.mediapool.web.client.dto.Movie;
import de.mediapool.web.client.gui.DataForm;
import de.mediapool.web.client.gui.ImageDialogBox;
import de.mediapool.web.client.gui.ImageForm;
import de.mediapool.web.client.gui.MovieListTable;
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

	List<Movie> movieList;
	Movie selectedMovie;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Use RootPanel.get() to get the entire body element

		RootPanel.get().setSize("1024", "768");

		MovieListTable movieTable = new MovieListTable(getMovieList());
		ScrollPanel scroll = new ScrollPanel();
		scroll.add(movieTable);
		RootPanel.get(LIST_VIEW).add(scroll);

		getMoviesForTable(movieTable);

		final SearchForm searchMovieForm = new SearchForm(mediaService, getMovieList());
		RootPanel.get(SEARCH_VIEW).add(searchMovieForm);

		final DataForm dataMovieForm = new DataForm(getSelectedMovie());
		RootPanel.get(DATA_VIEW).add(dataMovieForm);

		final ImageForm image = new ImageForm(getSelectedMovie());
		RootPanel.get(IMAGE_VIEW).add(image);

		final ImageDialogBox popup = new ImageDialogBox(getSelectedMovie());

		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popup.setMovie(getSelectedMovie());
				popup.refreshAndShowBox();
			}
		});

		movieTable.addCellPreviewHandler(new CellPreviewEvent.Handler<Movie>() {
			public void onCellPreview(CellPreviewEvent<Movie> event) {
				String type = event.getNativeEvent().getType();
				if (type.equals("click")) {
					setSelectedMovie(event.getValue());
					refreshLeftSide(dataMovieForm, image);
				}
			}
		});
	}

	private void refreshLeftSide(DataForm dataMovieForm, Image image) {
		dataMovieForm.setMovie(getSelectedMovie());
		dataMovieForm.refreshData();
		image.setUrl(getSelectedMovie().getImageUrl());
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
				setSelectedMovie(getMovieList().get(0));
			}
		});
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

}