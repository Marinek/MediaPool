package de.mediapool.core.persistence.dao.interfaces;

import java.util.List;

import de.mediapool.core.persistence.core.DBException;
import de.mediapool.core.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.core.persistence.vo.media.MediaAttributeVO;

public interface IMediaAttributesDAO extends IDataAccessObject<MediaAttributeVO> {

	public List<MediaAttributeVO> getAttributesFor(Integer pMediaId) throws DBException;
}
