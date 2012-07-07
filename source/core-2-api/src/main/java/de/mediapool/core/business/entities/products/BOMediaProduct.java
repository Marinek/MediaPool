package de.mediapool.core.business.entities.products;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.AttributedMediaBean;
import de.mediapool.core.beans.business.entity.products.AttributedProductBean;
import de.mediapool.core.business.entities.BOAbstractEntity;
import de.mediapool.core.exceptions.MPExeption;

public class BOMediaProduct extends BOAbstractEntity<AttributedProductBean> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected BOMediaProduct(AttributedProductBean pMediaBean, UserBean pUserBean) throws MPExeption {
		super(pUserBean, pMediaBean);

	}

	protected BOMediaProduct(UUID pMediaBean, UserBean pUserBean) throws MPExeption {
		super(pMediaBean, pUserBean);
		
	}
	
	protected BOMediaProduct(UserBean pUserBean) throws MPExeption {
		super(pUserBean);
	}

	public static BOMediaProduct getInstance(UserBean pUserBean) throws MPExeption {
		return new BOMediaProduct(pUserBean);
	}
	
	public static BOMediaProduct getInstance(AttributedProductBean pMediaBean, UserBean pUserBean) throws MPExeption {
		return new BOMediaProduct(pMediaBean, pUserBean);
	}
	
	public static BOMediaProduct getInstance(UUID pMediaBean, UserBean pUserBean) throws MPExeption {
		return new BOMediaProduct(pMediaBean, pUserBean);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public List<AttributedMediaBean> getMedia () throws MPExeption {
		List<AttributedMediaBean> lReturnList = new ArrayList<AttributedMediaBean>();

		return lReturnList;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected void protectedSave() throws MPExeption {
		
	}

	protected void protectedDelete() throws MPExeption {
		
	}

	protected AttributedProductBean getCurrentEntityBeanInstance() throws MPExeption {
		return new AttributedProductBean();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
