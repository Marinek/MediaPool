package de.mediapool.core.persistence.dao.interfaces.search;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.joined.relationship.ProductMediaJoinedVO;

public interface IProductMediaSearchDAO extends IPSDataAccessObject<ProductMediaJoinedVO>  {

	public List<ProductMediaJoinedVO> search () throws PSException;
}
