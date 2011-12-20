package de.mediapool.web.client.dto;

import java.io.Serializable;

public class Movie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1170383536974857505L;

	private int id;
	private String title;
	private String imageUrl;
	private String genre;

	public Movie() {

	}

	public Movie(int id, String title, String imageUrl, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
