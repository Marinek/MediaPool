package de.mediapool.core.business.entities.media;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.business.entities.BOAbstractEntity;
import de.mediapool.core.exceptions.MPExeption;

public class BOMedia extends BOAbstractEntity<MediaBean> {

	public BOMedia(UserBean pUserBean) throws MPExeption {
		super(pUserBean);
	}

	public BOMedia(UUID mediaID, UserBean pUserBean) throws MPExeption {
		super(mediaID, pUserBean);
	}

	protected void protectedSave() throws MPExeption {

	}

	protected void protectedDelete() throws MPExeption {

	}

	protected MediaBean getCurrentEntityBeanInstance() throws MPExeption {
		return new MediaBean();
	}

}