package de.mediapool.core.persistence.dao.interfaces.entities.products;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.entities.products.MediaProductVO;

public interface IMediaProductsDAO extends IPSDataAccessObject<MediaProductVO> {

	public List<MediaProductVO> getProductsByMediaId (Integer pMediaId) throws PSException; 
}
