package de.mediapool.core.persistence.dao.interfaces;

import java.util.List;

import de.mediapool.core.persistence.core.interfaces.IDataAccessObject;
import de.mediapool.core.persistence.vo.media.MediaVO;


public interface IMediaDAO<T extends MediaVO> extends IDataAccessObject<T> {

	public abstract List<T> getAll();
}
