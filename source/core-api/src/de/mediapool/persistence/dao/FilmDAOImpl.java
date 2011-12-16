package de.mediapool.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mediapool.persistence.core.AbstractDAOImpl;
import de.mediapool.persistence.dao.interfaces.IFilmDAO;
import de.mediapool.persistence.vo.MovieVO;


@Repository("filmDao")
@Transactional
public class FilmDAOImpl extends AbstractDAOImpl<MovieVO> implements IFilmDAO {

	public List<MovieVO> getAll() {
		System.out.println("FilmDaoImpl.getAll()");
		List<MovieVO> filme = hibernateTemplate.find("from MovieVO");
		return filme;
	}
	
	public Class<MovieVO> getValueObjectClass() {
		return MovieVO.class;
	}
}
