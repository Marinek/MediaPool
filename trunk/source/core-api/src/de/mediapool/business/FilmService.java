package de.mediapool.business;

import java.util.List;

import de.mediapool.persistence.vo.FilmVO;



public interface FilmService {
	public abstract void createFilm();
	public abstract void deleteFilm(FilmVO id);
	public abstract FilmVO getFilm(int id);
	public abstract List<FilmVO> getAll();
}
