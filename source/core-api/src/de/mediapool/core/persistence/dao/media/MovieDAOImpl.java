package de.mediapool.core.persistence.dao.media;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mediapool.core.persistence.dao.interfaces.IMovieDAO;
import de.mediapool.core.persistence.vo.media.MovieVO;

@Repository("movieDAO")
@Transactional
public class MovieDAOImpl extends AbstractMediaDAOImpl<MovieVO> implements IMovieDAO {

	public List<MovieVO> getAll() {
		return hibernateTemplate.find("from Movies");
	}

	public Class<MovieVO> getValueObjectClass() {
		return MovieVO.class;
	}

}
