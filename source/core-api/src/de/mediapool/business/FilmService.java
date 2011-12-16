package de.mediapool.business;

import java.util.List;

import de.mediapool.persistence.vo.MovieVO;



public interface FilmService {
	public abstract void createFilm(MovieVO movie);
	public abstract void deleteFilm(MovieVO id);
	public abstract MovieVO getFilm(int id);
	public abstract List<MovieVO> getAll();
}
