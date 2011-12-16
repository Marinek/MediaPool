package de.mediapool.business;

import java.util.List;

import de.mediapool.persistence.dao.interfaces.IFilmDAO;
import de.mediapool.persistence.vo.MovieVO;

public class FilmServiceImpl implements FilmService {
	private IFilmDAO filmDao;
	public void setFilmDao(IFilmDAO filmDao) {
		this.filmDao = filmDao;
	}
	public void createFilm(MovieVO movieVO) {
		this.filmDao.insert(movieVO);
	}
	public void deleteFilm(MovieVO id) {
		this.filmDao.delete(id);
	}
	public MovieVO getFilm(int id) {
		return this.filmDao.get(id);
	}
	public List<MovieVO> getAll() {
		return this.filmDao.getAll();
	}

}
