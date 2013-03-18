package de.mediapool.core.services.interfaces;

import java.util.List;

import de.mediapool.core.beans.business.entity.EntityTypeBean;
import de.mediapool.core.exceptions.MPException;

public interface IMediaMetaDataService extends IService {

	public List<EntityTypeBean> getMediaTypes() throws MPException;

}
