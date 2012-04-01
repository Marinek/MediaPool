package de.mediapool.core.persistence.dao.media;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.media.IMediaAttributeDefsDAO;
import de.mediapool.core.persistence.vo.media.MediaAttributeDefVO;

public class MediaAttributeDefsDAOImpl extends PSAbstractDAOImpl<MediaAttributeDefVO> implements IMediaAttributeDefsDAO {

	public Class<MediaAttributeDefVO> getValueObjectClass() {
		return MediaAttributeDefVO.class;
	}

}
