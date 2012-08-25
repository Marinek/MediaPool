package de.mediapool.core.business.search.entities;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.search.entity.AbstractEntitySearchBean;
import de.mediapool.core.beans.search.entity.EntityCriteriaBean;
import de.mediapool.core.beans.search.entity.EntityResultList;
import de.mediapool.core.business.search.BOAbstractSearch;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.builder.PSRelationshipCriteriaBuilder;
import de.mediapool.core.persistence.vo.entities.EntityVO;

public abstract class BOAbstractEntitySearch<S extends AbstractEntitySearchBean, R extends EntityResultList<?>> extends BOAbstractSearch<S,R> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	protected BOAbstractEntitySearch(UserBean pUserBean) throws MPExeption {
		super(pUserBean);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	public R executeSearch(S pSearchQuery) throws MPExeption {
		
		for(EntityCriteriaBean lCriteria : pSearchQuery.getCriteriaList()) {
			
		}
		
		try {
			EntityVO.getProductMediaSearchDAO().search();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.getSearchResultList();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
