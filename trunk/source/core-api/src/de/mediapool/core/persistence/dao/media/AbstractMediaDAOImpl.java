package de.mediapool.core.persistence.dao.media;

import org.springframework.transaction.annotation.Transactional;

import de.mediapool.core.persistence.core.AbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.IMediaDAO;


@Transactional
public abstract class AbstractMediaDAOImpl<T extends MediaVO> extends AbstractDAOImpl<T> implements IMediaDAO<T> {


}
