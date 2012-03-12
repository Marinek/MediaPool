package de.mediapool.core.persistence.dao.media;

import de.mediapool.core.persistence.core.AbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.IMediaAttributeDefsDAO;
import de.mediapool.core.persistence.vo.media.MediaAttributeVO;

public class MediaAttributeDefsDAOImpl extends AbstractDAOImpl<MediaAttributeVO> implements IMediaAttributeDefsDAO {

	public Class<MediaAttributeVO> getValueObjectClass() {
		return MediaAttributeVO.class;
	}

}
