package de.mediapool.core.persistence.dao.interfaces.entities;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.entities.EntityVO;

public interface IEntityDAO extends IPSDataAccessObject<EntityVO> {

	public abstract EntityVO getByPrimaryKey(String pPrimaryKey) throws PSException;

	public abstract List<EntityVO> getAllMedia() throws PSException;
}
