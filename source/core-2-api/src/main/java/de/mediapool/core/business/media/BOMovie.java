package de.mediapool.core.business.media;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.media.MediaType;
import de.mediapool.core.beans.media.MovieBean;
import de.mediapool.core.beans.utils.PersistenceUtils;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.DBException;
import de.mediapool.core.persistence.vo.media.MovieVO;

public class BOMovie extends BOAbstractMedia {

	protected BOMovie() throws MPExeption {
		super();
	}
	
	protected BOMovie(int mediaID) throws MPExeption {
		super(mediaID);
	}
	
	protected void init(int mediaID) throws MPExeption {
		try {
			MovieVO movieVO = MovieVO.getDAO().get(mediaID);
			if(movieVO != null) {
				this.setCurrentMediaBean(this.getMediaBean(movieVO));
			}
		} catch (DBException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Could not Read MovieVO", e);
		}
		
		super.init(mediaID);
	}

	public static BOMovie getInstance() throws MPExeption {
		return new BOMovie();
	}
	
	public static BOMovie getInstance(Integer mediaID) throws MPExeption {
		return new BOMovie(mediaID);
	}

	protected void protectedSave() throws MPExeption {
		MovieVO saveMovieVO = this.getMovieVO();

		try {
			saveMovieVO = MovieVO.getDAO().insert(saveMovieVO);
			
			this.setCurrentMediaBean(this.getMediaBean(saveMovieVO));
		} catch (DBException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_UPDATE, "Could not Update MovieVO", e);
		}
	}
	
	protected void protectedDelete() throws MPExeption {
		MovieVO deleteMovie = this.getMovieVO();
		
		try {
			MovieVO.getDAO().delete(deleteMovie);
		} catch (DBException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_DELETE, "Could not Update MovieVO", e);
		}
	}
	
	public MovieBean getCurrentMediaBean() throws MPExeption {
		return (MovieBean) super.getCurrentMediaBean();
	}
	
	protected MediaType getMediaType() throws MPExeption {
		return MediaType.MOVIE;
	}
	
	private MovieVO getMovieVO() throws MPExeption {
		return PersistenceUtils.<MovieVO>toVO(MovieVO.class, this.getCurrentMediaBean());
	}
	
	private AbstractMediaBean getMediaBean(MovieVO movieVO) {
		return PersistenceUtils.<MovieBean>toBean(MovieBean.class, movieVO);
	}

}
