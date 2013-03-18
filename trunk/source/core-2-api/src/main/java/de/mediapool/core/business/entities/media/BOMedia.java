package de.mediapool.core.business.entities.media;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.business.entities.BOAbstractEntity;
import de.mediapool.core.exceptions.MPException;

public class BOMedia extends BOAbstractEntity<MediaBean> {

	public BOMedia(UserBean pUserBean) throws MPException {
		super(pUserBean);
	}

	public BOMedia(UUID mediaID, UserBean pUserBean) throws MPException {
		super(mediaID, pUserBean);
	}

	protected void protectedSave() throws MPException {

	}

	protected void protectedDelete() throws MPException {

	}

	protected MediaBean getCurrentEntityBeanInstance() throws MPException {
		return new MediaBean();
	}

}
