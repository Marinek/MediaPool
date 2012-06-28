package de.mediapool.core.persistence.dao.interfaces.media;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.media.EntityVO;


public interface IMediaDAO extends IPSDataAccessObject<EntityVO> {

	public abstract List<EntityVO> getAll();
	
	public EntityVO getByPrimaryKey(Integer pPrimaryKey) throws PSException;
}
