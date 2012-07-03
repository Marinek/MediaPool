package de.mediapool.core.persistence.dao.entities;

import org.springframework.stereotype.Service;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityTypeDAO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;

@Service
public class EntityTypeDAOImpl extends PSAbstractDAOImpl<EntityTypeVO> implements IEntityTypeDAO {

	protected Class<EntityTypeVO> getValueObjectClass() throws PSException {
		return EntityTypeVO.class;
	}

}
