package de.mediapool.core.persistence.dao.media;

import de.mediapool.core.persistence.core.AbstractDAOImpl;
import de.mediapool.core.persistence.dao.interfaces.IMediaAttributeDAO;
import de.mediapool.core.persistence.vo.media.MediaAttribute;

public class MediaAttributeDAOImpl extends AbstractDAOImpl<MediaAttribute> implements IMediaAttributeDAO {

	public Class<MediaAttribute> getValueObjectClass() {
		return MediaAttribute.class;
	}

}
