package de.mediapool.core.persistence.dao.media;

import java.util.List;

import de.mediapool.core.persistence.core.AbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.IMediaDAO;
import de.mediapool.core.persistence.vo.media.MediaVO;


public class MediaDAOImpl extends AbstractDAOImpl<MediaVO> implements IMediaDAO {

	public Class<MediaVO> getValueObjectClass() {
		return MediaVO.class;
	}

	public List<MediaVO> getAll() {
		return null;
	}


}
