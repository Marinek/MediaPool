package de.mediapool.business;

import java.util.List;

import de.mediapool.persistence.dao.interfaces.IFilmDAO;
import de.mediapool.persistence.vo.FilmVO;

public class FilmServiceImpl implements FilmService {
	private IFilmDAO filmDao;
	public void setFilmDao(IFilmDAO filmDao) {
		this.filmDao = filmDao;
	}
	public void createFilm() {
		this.filmDao.insert(new FilmVO());
	}
	public void deleteFilm(FilmVO id) {
		this.filmDao.delete(id);
	}
	public FilmVO getFilm(int id) {
		return this.filmDao.get(id);
	}
	public List<FilmVO> getAll() {
		return this.filmDao.getAll();
	}

}
