package de.mediapool.core.persistence.dao.interfaces.media;

import java.util.List;
import java.util.UUID;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.media.EntityAttributeVO;

public interface IMediaAttributesDAO extends IPSDataAccessObject<EntityAttributeVO> {

	public List<EntityAttributeVO> getAttributesFor(UUID pMediaId) throws PSException;
}
