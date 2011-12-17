package de.mediapool.business.media;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.beans.media.AbstractMediaBean;
import de.mediapool.business.AbstractServiceImpl;
import de.mediapool.business.interfaces.IMediaService;
import de.mediapool.exceptions.MPExeption;
import de.mediapool.persistence.dao.interfaces.IMediaDAO;
import de.mediapool.persistence.vo.media.MediaVO;

public abstract class AbstractMediaServiceImpl extends AbstractServiceImpl implements IMediaService {

	public void createMedia(AbstractMediaBean mediaBean) throws MPExeption {
		MediaVO mediaVO = this.getVO(mediaBean);
		this.getMediaDAO().insert(mediaVO);
		
		mediaBean.setId(mediaVO.getId());
	}

	public void deleteMedia(AbstractMediaBean mediaBean) throws MPExeption {
		this.getMediaDAO().delete(this.getVO(mediaBean));
	}

	public AbstractMediaBean getMedia(int mediaId) throws MPExeption {
		MediaVO mediaVO = this.getMediaDAO().get(mediaId);
		
		if(mediaVO != null) {
			return this.getBean(mediaVO);
		}
		
		return null;
	}

	public List<AbstractMediaBean> getAll() throws MPExeption {
		List<AbstractMediaBean> returnList = new ArrayList<AbstractMediaBean>();
		
		for(MediaVO mediaVO : this.getMediaDAO().getAll()) {
			returnList.add(this.getBean(mediaVO));
		}
		
		return null;
	}
	
	private IMediaDAO<MediaVO> getMediaDAO() {
		return (IMediaDAO<MediaVO>) this.getDAO();
	}
	
	protected abstract MediaVO getVO(AbstractMediaBean mediaBean);
	
	protected abstract AbstractMediaBean getBean(MediaVO mediaBean);

}
