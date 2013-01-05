package de.mediapool.web.server.core;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.web.client.dto.Movie;

public class MovieService {

	private static List<Movie> movieList = new ArrayList<Movie>();

	private static List<Movie> getAllMovies() {
		movieList = new ArrayList<Movie>();
		Movie m1 = new Movie(1, "Der Pate", "cover/Der Pate - Cover.jpg", "Gangster");
		Movie m2 = new Movie(2, "Der Pate II", "cover/Der Pate 2 - Cover.jpg", "Gangster");
		Movie m3 = new Movie(3, "Der Pate III", "cover/Der Pate 3 - Cover.jpg", "Gangster");
		movieList.add(m1);
		movieList.add(m2);
		movieList.add(m3);

		int anzahl = 50;
		for (int i = 0; i < anzahl; i++) {
			movieList.add(new Movie(3 + i, "Der Pate " + i, "Bild" + i, "Gangster"));
		}

		return movieList;

	}

	public static List<Movie> searchMedia(Movie movie) {
		List<Movie> searchList = new ArrayList<Movie>();
		String title = movie.getTitle() != null ? movie.getTitle() : "";

		for (Movie m : getAllMovies()) {
			if (title.equalsIgnoreCase(m.getTitle())) {
				searchList.add(m);
			}
		}

		if (("").equals(title)) {
			searchList = movieList;
		}
		return searchList;

	}

	public static List<Movie> updateMedia(Movie movie) {
		for (Movie m : movieList) {
			if (m.getId() == movie.getId()) {
				m.setTitle(movie.getTitle());
			}
		}
		return movieList;
	}

	public static List<Movie> deleteMedia(Movie movie) {
		List<Movie> deleteList = new ArrayList<Movie>();

		for (Movie m : movieList) {
			if (m.getId() != movie.getId()) {
				deleteList.add(m);
			}
		}
		return deleteList;
	}

	public static List<Movie> createMedia(Movie movie) {
		movie.setId(getNextId());
		movieList.add(movie);
		return movieList;
	}

	private static int getNextId() {
		int next_id = 0;
		for (Movie movie : movieList) {
			if (movie.getId() > next_id) {
				next_id = movie.getId();
			}
		}
		return next_id + 1;
	}

}
