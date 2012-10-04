package de.mediapool.core;

import java.util.ArrayList;
import java.util.List;

public class TestData {

	private static final String movie1[] = { "90", "image.jpg", "spannend", "Action", "Thriller", "Deutsch", "Der Pate", "Godfather", "Spielfilm", "12", "1980" };
	private static final String movie2[] = { "90", "image.jpg", "spannend", "Action", "Thriller", "Deutsch", "Der Pate II", "Godfather II", "Spielfilm", "12", "1980" };
	private static final String movie3[] = { "90", "image.jpg", "spannend", "Action", "Thriller", "Deutsch", "Der Pate III", "Godfather III", "Spielfilm", "12", "1980" };

	private static final String product1[] = { "12345678", "Blueray", "cover.jpg", "english", "good", "uncut", "16", "120", "1", "10", "01.01.1980" };
	private static final String product2[] = { "12345678", "Blueray", "cover.jpg", "english", "good", "uncut", "16", "120", "1", "10", "01.01.1980" };
	private static final String product3[] = { "12345678", "Blueray", "cover.jpg", "english", "good", "uncut", "16", "120", "1", "10", "01.01.1980" };

	public static List<String[]> getProducts() {
		List<String[]> products = new ArrayList<String[]>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		return products;
	}

	public static List<String[]> getMovies() {
		List<String[]> movies = new ArrayList<String[]>();
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
		return movies;
	}

}
