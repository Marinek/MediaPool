package de.mediapool.core.persistence.dao.entitys;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.media.IEntityAttributeDefsDAO;
import de.mediapool.core.persistence.vo.media.EntityAttributeDefVO;

public class EntityAttributeDefsDAOImpl extends PSAbstractDAOImpl<EntityAttributeDefVO> implements IEntityAttributeDefsDAO {

	public Class<EntityAttributeDefVO> getValueObjectClass() {
		return EntityAttributeDefVO.class;
	}

}
