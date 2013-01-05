package de.mediapool.web.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

import de.mediapool.web.client.MediaService;
import de.mediapool.web.client.MediaServiceAsync;
import de.mediapool.web.client.dto.Movie;

public class MediaFormWidgets {

	private static MediaFormWidgets instance = null;
	private HashMap<String, Widget> map;
	private List<Movie> movieList;
	private Movie selectedMovie;

	private MediaServiceAsync mediaService;

	private MediaFormWidgets() {
	}

	public static MediaFormWidgets getInstance() {
		if (instance == null) {
			instance = new MediaFormWidgets();
			instance.map = new HashMap<String, Widget>();
			instance.selectedMovie = new Movie();
			instance.movieList = new ArrayList<Movie>();
			instance.mediaService = GWT.create(MediaService.class);
		}

		return instance;

	}

	public void updateListView(List<Movie> list) {
		this.setMovieList(list);

		this.setSelectedMovie(list.get(0));
		((ListForm) this.getWidget("ListForm")).getMovieTable().fillMovieTable();
	}

	public void addWidget(String key, Widget value) {
		map.put(key, value);
	}

	public Widget getWidget(String key) {
		return map.get(key);
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

	public MediaServiceAsync getMediaService() {
		return mediaService;
	}

}
