package de.mediapool.core.business.media.attributes;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.MPExeption;

public class BOMediaAttribute extends BusinessObject {

	protected BOMediaAttribute(UserBean pUserBean) throws MPExeption {
		super(pUserBean);
	}

}
