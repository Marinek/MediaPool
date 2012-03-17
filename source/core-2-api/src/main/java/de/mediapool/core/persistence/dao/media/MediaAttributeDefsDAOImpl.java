package de.mediapool.core.persistence.dao.media;

import de.mediapool.core.persistence.core.AbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.IMediaAttributeDefsDAO;
import de.mediapool.core.persistence.vo.media.MediaAttributeDefVO;

public class MediaAttributeDefsDAOImpl extends AbstractDAOImpl<MediaAttributeDefVO> implements IMediaAttributeDefsDAO {

	public Class<MediaAttributeDefVO> getValueObjectClass() {
		return MediaAttributeDefVO.class;
	}

}
