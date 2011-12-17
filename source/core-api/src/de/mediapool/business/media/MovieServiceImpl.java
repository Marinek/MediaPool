package de.mediapool.business.media;

import de.mediapool.beans.media.AbstractMediaBean;
import de.mediapool.beans.media.MovieBean;
import de.mediapool.business.interfaces.IMovieService;
import de.mediapool.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.persistence.core.interfaces.IValueObject;
import de.mediapool.persistence.dao.interfaces.IMovieDAO;
import de.mediapool.persistence.vo.media.MediaVO;
import de.mediapool.persistence.vo.media.MovieVO;

public class MovieServiceImpl extends AbstractMediaServiceImpl implements IMovieService {
	
	public IMovieDAO contextMediaDAO;
	
	public void setContextMediaDAO(IMovieDAO contextMediaDAO) {
		this.contextMediaDAO = contextMediaDAO;
	}

	public IDataAccessObject<? extends IValueObject> getDAO() {
		return this.contextMediaDAO;
	}

	protected MediaVO getVO(AbstractMediaBean mediaBean) {
		MovieVO movieVO = new MovieVO();
		MovieBean movieBean = (MovieBean)mediaBean;
		
		movieVO.setId(movieBean.getId());
		movieVO.setLengthMinutes(movieBean.getLength());
		movieVO.setName(movieBean.getName());
		
		return movieVO;
		
	}

	protected AbstractMediaBean getBean(MediaVO mediaVO) {
		MovieBean movieBean = new MovieBean();
		MovieVO movieVO = (MovieVO)mediaVO;
		
		movieBean.setId(movieVO.getId());
		movieBean.setName(movieVO.getName());
		movieBean.setLength(movieVO.getLengthMinutes());
		
		return null;
	}
}
