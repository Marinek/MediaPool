package de.mediapool.core.persistence.dao.interfaces.search;

import java.util.List;

import de.mediapool.core.beans.search.entity.joined.ProductMediaSearchBean;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.joined.relationship.ProductMediaJoinedVO;

public interface IProductMediaSearchDAO extends IPSDataAccessObject<ProductMediaJoinedVO> {

	public List<ProductMediaJoinedVO> search(ProductMediaSearchBean searchCriteria) throws PSException;
}
