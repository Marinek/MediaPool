package de.mediapool.core.persistence.dao.entitys;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.media.IMediaAttributeDefsDAO;
import de.mediapool.core.persistence.vo.media.EntityAttributeDefVO;

public class MediaAttributeDefsDAOImpl extends PSAbstractDAOImpl<EntityAttributeDefVO> implements IMediaAttributeDefsDAO {

	public Class<EntityAttributeDefVO> getValueObjectClass() {
		return EntityAttributeDefVO.class;
	}

}
