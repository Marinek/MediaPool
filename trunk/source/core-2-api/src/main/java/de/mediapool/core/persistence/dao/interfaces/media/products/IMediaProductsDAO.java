package de.mediapool.core.persistence.dao.interfaces.media.products;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.media.products.MediaProductVO;

public interface IMediaProductsDAO extends IPSDataAccessObject<MediaProductVO> {

	public List<MediaProductVO> getProductsByMediaId (Integer pMediaId) throws PSException; 
}
