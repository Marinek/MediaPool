package de.mediapool.persistence;

import de.mediapool.beans.Film;

public interface FilmDao {
	public abstract void insert(Film film);
	public abstract void update(Film film);
	public abstract void delete(int number);
	public abstract Film get(int number);
}
