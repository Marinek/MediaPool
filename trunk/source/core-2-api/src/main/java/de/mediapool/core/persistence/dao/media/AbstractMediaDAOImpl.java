package de.mediapool.core.persistence.dao.media;

import de.mediapool.core.persistence.core.AbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.IMediaDAO;
import de.mediapool.core.persistence.vo.media.MediaVO;


public abstract class AbstractMediaDAOImpl<T extends MediaVO> extends AbstractDAOImpl<T> implements IMediaDAO<T> {


}
