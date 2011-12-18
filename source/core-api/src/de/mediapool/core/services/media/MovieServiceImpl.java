package de.mediapool.core.services.media;

import de.mediapool.core.beans.media.MovieBean;
import de.mediapool.core.persistence.dao.media.MovieVO;
import de.mediapool.core.services.interfaces.IMovieService;

public class MovieServiceImpl extends AbstractMediaServiceImpl implements IMovieService {

	public MovieBean getCurrentContextualBean() {
		MovieBean movieBean = null;
		
		if( this.currentContextualBean == null) {
			movieBean = new MovieBean();
			this.currentContextualBean = movieBean;
		} else {
			movieBean = (MovieBean) currentContextualBean;
		}
		
		movieBean.setLength(((MovieVO) this.currentContextualVO).getLengthMinutes());
		
		return (MovieBean) super.getCurrentContextualBean();
	}

	protected MovieVO getCurrentContextualVO() {
		MovieVO movieVO = null;
		
		if(this.currentContextualVO == null) {
			movieVO = new MovieVO();
			this.currentContextualVO = movieVO;
		} else {
			movieVO = (MovieVO) this.currentContextualVO;
		}
		
		movieVO.setLengthMinutes(((MovieBean) this.currentContextualBean).getLength());
		
		return (MovieVO) super.getCurrentContextualVO();
	}
	


}
