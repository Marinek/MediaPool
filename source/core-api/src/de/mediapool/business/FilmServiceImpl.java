package de.mediapool.business;

import java.util.List;

import de.mediapool.beans.Film;
import de.mediapool.persistence.FilmDao;




public class FilmServiceImpl implements FilmService {
	private FilmDao filmDao;
	public void setFilmDao(FilmDao filmDao) {
		this.filmDao = filmDao;
	}
	public void createFilm() {
		this.filmDao.insert(new Film());
	}
	public void deleteFilm(int id) {
		this.filmDao.delete(id);
	}
	public Film getFilm(int id) {
		return this.filmDao.get(id);
	}
	public List<Film> getAll() {
		return this.filmDao.getAll();
	}

}
