package de.mediapool.business;

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
	public void deleteFilm(int number) {
		this.filmDao.delete(number);
	}
	public Film getFilm(int number) {
		return this.filmDao.get(number);
	}

}
