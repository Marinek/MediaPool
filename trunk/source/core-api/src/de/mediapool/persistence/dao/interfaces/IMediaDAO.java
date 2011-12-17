package de.mediapool.persistence.dao.interfaces;

import java.util.List;

import de.mediapool.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.persistence.vo.media.MediaVO;


public interface IMediaDAO<T extends MediaVO> extends IDataAccessObject<T> {

	public abstract List<T> getAll();
}
