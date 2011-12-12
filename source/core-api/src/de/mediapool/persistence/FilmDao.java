package de.mediapool.coreapi.persistence;

import java.util.List;

import de.mediapool.coreapi.beans.Film;

public interface FilmDao {
	public abstract void insert(Film film);
	public abstract void update(Film film);
	public abstract void delete(int id);
	public abstract Film get(int id);
	public abstract List<Film> getAll();
}
