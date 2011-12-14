package de.mediapool.persistence.dao.interfaces;

import java.util.List;

import de.mediapool.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.persistence.vo.FilmVO;


public interface IFilmDAO extends IDataAccessObject<FilmVO> {

	public abstract List<FilmVO> getAll();
}
