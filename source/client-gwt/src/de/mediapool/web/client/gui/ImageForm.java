package de.mediapool.web.client.gui;

import com.google.gwt.user.client.ui.Image;

import de.mediapool.web.client.dto.Movie;

public class ImageForm extends Image {

	private Movie movie;

	public ImageForm(Movie movie) {
		setMovie(movie);
		String url = "";
		if (getMovie() != null) {
			url = getMovie().getImageUrl();
		}
		this.setUrl(url);
		this.setSize("422px", "");
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
