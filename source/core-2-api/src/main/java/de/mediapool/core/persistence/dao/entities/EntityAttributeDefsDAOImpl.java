package de.mediapool.core.persistence.dao.entities;

import org.springframework.stereotype.Service;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityAttributeDefsDAO;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;

@Service
public class EntityAttributeDefsDAOImpl extends PSAbstractDAOImpl<EntityAttributeDefVO> implements IEntityAttributeDefsDAO {

	public Class<EntityAttributeDefVO> getValueObjectClass() {
		return EntityAttributeDefVO.class;
	}

}
