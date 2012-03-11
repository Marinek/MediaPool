package de.mediapool.core.persistence.dao.interfaces;

import java.util.List;

import javax.print.attribute.standard.Media;

import de.mediapool.core.persistence.core.DBException;
import de.mediapool.core.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.core.persistence.vo.media.MediaVO;


public interface IMediaDAO extends IDataAccessObject<MediaVO> {

	public abstract List<MediaVO> getAll();
	
	public MediaVO getByPrimaryKey(Integer pPrimaryKey) throws DBException;
}
