package de.mediapool.web.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;

import de.mediapool.web.client.dto.Movie;

public class MediaFormWidgets {

	private static MediaFormWidgets instance = null;
	private HashMap<String, Widget> map;
	private List<Movie> movieList;
	private Movie selectedMovie;

	private MediaFormWidgets() {
	}

	public static MediaFormWidgets getInstance() {
		if (instance == null) {
			instance = new MediaFormWidgets();
			instance.map = new HashMap<String, Widget>();
			instance.selectedMovie = new Movie();
			instance.movieList = new ArrayList<Movie>();
		}

		return instance;

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

}
