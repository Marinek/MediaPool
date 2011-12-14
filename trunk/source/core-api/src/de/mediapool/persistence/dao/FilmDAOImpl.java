package de.mediapool.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mediapool.persistence.core.AbstractDAOImpl;
import de.mediapool.persistence.dao.interfaces.IFilmDAO;
import de.mediapool.persistence.vo.FilmVO;


@Repository("filmDao")
@Transactional
public class FilmDAOImpl extends AbstractDAOImpl<FilmVO> implements IFilmDAO {

	public List<FilmVO> getAll() {
		System.out.println("FilmDaoImpl.getAll()");
		List<FilmVO> filme = hibernateTemplate.find("from FilmVO");
		return filme;
	}
	
	public Class<FilmVO> getValueObjectClass() {
		return FilmVO.class;
	}
}
