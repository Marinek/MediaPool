package de.mediapool.core.persistence.dao.interfaces.entities;

import java.util.List;
import java.util.UUID;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.entities.EntityVO;


public interface IEntityDAO extends IPSDataAccessObject<EntityVO> {

	public abstract List<EntityVO> getAll();
	
	public EntityVO getByPrimaryKey(UUID pPrimaryKey) throws PSException;
}
