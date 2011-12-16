package de.mediapool.persistence.dao.interfaces;

import java.util.List;

import de.mediapool.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.persistence.vo.MovieVO;


public interface IFilmDAO extends IDataAccessObject<MovieVO> {

	public abstract List<MovieVO> getAll();
}
