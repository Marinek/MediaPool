package de.mediapool.business;

import java.util.List;

import de.mediapool.beans.Film;


public interface FilmService {
	public abstract void createFilm();
	public abstract void deleteFilm(int id);
	public abstract Film getFilm(int id);
	public abstract List<Film> getAll();
}
