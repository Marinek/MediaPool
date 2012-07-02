package de.mediapool.core.persistence.dao.entitys;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.dao.interfaces.media.IEntityTypeDAO;
import de.mediapool.core.persistence.vo.media.EntityTypeVO;

public class EntityTypeDAOImpl extends PSAbstractDAOImpl<EntityTypeVO> implements IEntityTypeDAO {

	protected Class<EntityTypeVO> getValueObjectClass() throws PSException {
		return EntityTypeVO.class;
	}

}
