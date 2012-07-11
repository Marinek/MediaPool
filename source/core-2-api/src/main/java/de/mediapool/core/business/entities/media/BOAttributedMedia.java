package de.mediapool.core.business.entities.media;

import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.AttributedMediaBean;
import de.mediapool.core.business.entities.BOAbstractEntity;
import de.mediapool.core.exceptions.MPExeption;

public class BOAttributedMedia extends BOAbstractEntity<AttributedMediaBean> {

	public BOAttributedMedia(UserBean pUserBean) throws MPExeption {
		super(pUserBean);
	}

	public BOAttributedMedia(UUID mediaID, UserBean pUserBean) throws MPExeption {
		super(mediaID, pUserBean);
	}

	protected void protectedSave() throws MPExeption {
		
	}

	protected void protectedDelete() throws MPExeption {

	}

	protected AttributedMediaBean getCurrentEntityBeanInstance() throws MPExeption {
		return new AttributedMediaBean();
	}

}