package de.mediapool.persistence.dao.media;

import org.springframework.transaction.annotation.Transactional;

import de.mediapool.persistence.core.AbstractDAOImpl;
import de.mediapool.persistence.dao.interfaces.IMediaDAO;
import de.mediapool.persistence.vo.media.MediaVO;


@Transactional
public abstract class AbstractMediaDAOImpl<T extends MediaVO> extends AbstractDAOImpl<T> implements IMediaDAO<T> {


}
