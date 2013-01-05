package de.mediapool.web.client.gui;

import java.util.List;

import com.google.gwt.user.client.ui.ScrollPanel;

import de.mediapool.web.client.dto.Movie;

public class ListForm extends ScrollPanel {

	private MovieListTable movieTable;

	public ListForm() {
		movieTable = new MovieListTable(getMovieList());
		this.setStyleName("list_view_panel");

		this.add(movieTable);
		this.setAlwaysShowScrollBars(true);
	}

	public List<Movie> getMovieList() {
		return getMfw().getMovieList();
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}

	public MovieListTable getMovieTable() {
		return movieTable;
	}

	public void setMovieTable(MovieListTable movieTable) {
		this.movieTable = movieTable;
	}

}
