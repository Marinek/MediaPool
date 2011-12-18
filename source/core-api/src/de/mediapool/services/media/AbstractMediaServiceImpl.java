package de.mediapool.services.media;

import de.mediapool.beans.media.AbstractMediaBean;
import de.mediapool.beans.media.MovieBean;
import de.mediapool.exceptions.MPExeption;
import de.mediapool.persistence.dao.interfaces.IMediaDAO;
import de.mediapool.persistence.vo.media.MediaVO;
import de.mediapool.services.AbstractServiceImpl;
import de.mediapool.services.interfaces.IMediaService;

public abstract class AbstractMediaServiceImpl extends AbstractServiceImpl implements IMediaService {

	public AbstractMediaBean createMedia() throws MPExeption {
		this.getCurrentContextualDAO().insert(this.getCurrentContextualVO());
		
		return this.getCurrentContextualBean();
	}

	public void deleteMedia() throws MPExeption {
		this.getCurrentContextualDAO().delete(this.getCurrentContextualVO());
	}
	
	protected void init() {
		if(this.currentContextualBean != null) {
			int id = ((AbstractMediaBean) currentContextualBean).getId();
			if(id != 0) {
				this.currentContextualVO = this.getCurrentContextualDAO().get(id);
			}
		}
	}
	
	protected MediaVO getCurrentContextualVO() {
		MediaVO mediaVO = (MediaVO) this.currentContextualVO;
		
		AbstractMediaBean mediaBean = (AbstractMediaBean) this.currentContextualBean;
		
		mediaVO.setId(mediaBean.getId());
		mediaVO.setName(mediaBean.getName());
		
		return (MediaVO) super.getCurrentContextualVO();
	}
	
	public AbstractMediaBean getCurrentContextualBean() {
		AbstractMediaBean mediaBean = (MovieBean) currentContextualBean;
		
		MediaVO mediaVO = (MediaVO) this.currentContextualVO;
		
		mediaBean.setId(mediaVO.getId());
		mediaBean.setName(mediaVO.getName());
		
		return (MovieBean) super.getCurrentContextualBean();
	}
	
	protected IMediaDAO<MediaVO> getCurrentContextualDAO() {
		return (IMediaDAO<MediaVO>) super.getCurrentContextualDAO();
	}
	
	

}