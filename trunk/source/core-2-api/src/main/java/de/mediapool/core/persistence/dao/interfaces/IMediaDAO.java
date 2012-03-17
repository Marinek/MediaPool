package de.mediapool.core.persistence.dao.interfaces;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.media.MediaVO;


public interface IMediaDAO extends IPSDataAccessObject<MediaVO> {

	public abstract List<MediaVO> getAll();
	
	public MediaVO getByPrimaryKey(Integer pPrimaryKey) throws PSException;
}
