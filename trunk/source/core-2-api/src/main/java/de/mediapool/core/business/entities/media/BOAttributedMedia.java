package de.mediapool.core.business.entities.media;

import java.util.UUID;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.entity.attributes.AttributedMediaBean;
import de.mediapool.core.business.entities.BOAbstractEntity;
import de.mediapool.core.exceptions.MPExeption;

public class BOAttributedMedia extends BOAbstractEntity<AttributedMediaBean> {

	protected BOAttributedMedia(UserBean pUserBean) throws MPExeption {
		super(pUserBean);
	}

	protected BOAttributedMedia(UUID mediaID, UserBean pUserBean) throws MPExeption {
		super(mediaID, pUserBean);
	}

	public static BOAttributedMedia getInstance(UserBean pUserBean) throws MPExeption {
		return new BOAttributedMedia(pUserBean);
	}

	public static BOAttributedMedia getInstance(UUID mediaID, UserBean pUserBean) throws MPExeption {
		return new BOAttributedMedia(mediaID, pUserBean);
	}

	protected void protectedSave() throws MPExeption {
		
	}

	protected void protectedDelete() throws MPExeption {

	}

	protected AttributedMediaBean getCurrentEntityBeanInstance() throws MPExeption {
		return new AttributedMediaBean();
	}

}
