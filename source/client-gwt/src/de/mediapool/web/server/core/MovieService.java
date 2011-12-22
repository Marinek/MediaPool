package de.mediapool.web.server.core;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.web.client.dto.Movie;

public class MovieService {

	public static List<Movie> getallMovies() {
		List<Movie> movieList = new ArrayList<Movie>();
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

}
