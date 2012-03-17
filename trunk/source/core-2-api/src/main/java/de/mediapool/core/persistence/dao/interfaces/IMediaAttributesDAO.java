package de.mediapool.core.persistence.dao.interfaces;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.media.MediaAttributeVO;

public interface IMediaAttributesDAO extends IPSDataAccessObject<MediaAttributeVO> {

	public List<MediaAttributeVO> getAttributesFor(Integer pMediaId) throws PSException;
}
