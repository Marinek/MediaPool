package de.mediapool.core.persistence.dao.media;

import java.util.List;

import de.mediapool.core.persistence.dao.interfaces.IMovieDAO;
import de.mediapool.core.persistence.vo.media.MovieVO;

public class MovieDAOImpl extends AbstractMediaDAOImpl<MovieVO> implements IMovieDAO {

	public List<MovieVO> getAll() {
		return null;
	}

	public Class<MovieVO> getValueObjectClass() {
		return MovieVO.class;
	}

}
