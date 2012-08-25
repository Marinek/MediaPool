package de.mediapool.core.persistence.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

public class PSRelationshipCriteriaBuilder extends PSJoinedCriteriaBuilder {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	private Class<? extends IPSValueObject> parent;
	private Class<? extends IPSValueObject> child;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public PSRelationshipCriteriaBuilder(Class<? extends IPSValueObject> pParent,Class<? extends IPSValueObject> pChild) throws PSException {
		super(RelationshipVO.class, new ArrayList<Class<? extends IPSValueObject>>());
		
		this.parent = pParent;
		this.child = pChild;
		
		this.init();
	}

	private void init() throws PSException {
		this.createJoinFetch("p", this.parent);
		
		this.currentCriteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//		this.createJoinFetch("c", this.child);
		
//		this.currentCriteria.add(Restrictions.eqProperty(
//				this.getPropertyWithAlias(RelationshipVO.class, "child"),
//				this.getPropertyWithAlias("c", this.child, "id")
//			));
//		
//		this.currentCriteria.add(Restrictions.eqProperty(
//				this.getPropertyWithAlias(RelationshipVO.class, "child"),
//				this.getPropertyWithAlias("p", this.parent, "id")
//			));
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	private static List<Class<? extends IPSValueObject>> asList(Class<? extends IPSValueObject> pParent, Class<? extends IPSValueObject> pChild) {
		ArrayList<Class<? extends IPSValueObject>> lList = new ArrayList<Class<? extends IPSValueObject>>();
		
		lList.add(pParent);
		lList.add(pChild);
		
		return lList;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
