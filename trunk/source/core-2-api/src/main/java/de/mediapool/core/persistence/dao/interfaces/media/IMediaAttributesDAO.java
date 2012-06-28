package de.mediapool.core.persistence.dao.interfaces.media;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.media.EntityAttributeVO;

public interface IMediaAttributesDAO extends IPSDataAccessObject<EntityAttributeVO> {

	public List<EntityAttributeVO> getAttributesFor(Integer pMediaId) throws PSException;
}
