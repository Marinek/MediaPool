package de.mediapool.web.server.core;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.web.client.dto.Movie;

public class MovieService {
	
	public static List<Movie> getallMovies()
	{
		List<Movie> movieList = new ArrayList<Movie> ();
		Movie m1 = new Movie(1, "Der Pate", "Bild1", "Gangster");
		Movie m2 = new Movie(2, "Der Pate II", "Bild2", "Gangster");
		Movie m3 = new Movie(3, "Der Pate III", "Bild3", "Gangster");
		movieList.add(m1);
		movieList.add(m2);
		movieList.add(m3);
		return movieList;
		
	}

}
