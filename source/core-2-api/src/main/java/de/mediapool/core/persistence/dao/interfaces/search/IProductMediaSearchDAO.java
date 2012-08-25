package de.mediapool.core.persistence.dao.interfaces.search;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.entities.EntityVO;

public interface IProductMediaSearchDAO extends IPSDataAccessObject<EntityVO>  {

	public List<EntityVO> search () throws PSException;
}
