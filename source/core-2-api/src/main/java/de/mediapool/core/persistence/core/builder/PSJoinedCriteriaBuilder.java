package de.mediapool.core.persistence.core.builder;

import java.util.List;

import de.mediapool.core.persistence.core.PSCriteriaBuilder;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

public class PSJoinedCriteriaBuilder extends PSCriteriaBuilder {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public PSJoinedCriteriaBuilder(Class<? extends IPSValueObject> pRootClass, List<Class<? extends IPSValueObject>> pJoins) throws PSException {
		super(pRootClass);

		this.createJoinFetch(pJoins);
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public void createJoinFetch ( List<Class<? extends IPSValueObject>> pJoins) throws PSException {
		for(Class<? extends IPSValueObject> lJoin : pJoins) {
			this.createJoinFetch("", lJoin);
		}
	}
	
	public void createJoinFetch ( String pPrefix, Class<? extends IPSValueObject> pJoin) throws PSException {
//		this.currentCriteria.createAlias(pPrefix, this.getJoinAlias(pPrefix, pJoin), CriteriaSpecification.INNER_JOIN);
		this.currentCriteria.createAlias("childEntity", "e");
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	protected String getJoinAlias(Class<? extends IPSValueObject> lJoin) {
		return this.getJoinAlias("", lJoin);
	}

	protected String getJoinAlias(String pPrefix, Class<? extends IPSValueObject> lJoin) {
		return "_" + pPrefix + "_" + lJoin.getCanonicalName();
	}
	
	protected String getPropertyWithAlias(Class<? extends IPSValueObject> entityClass, String fieldName) {
		return this.getPropertyWithAlias("", entityClass, fieldName);
	}
	
	protected String getPropertyWithAlias(String pPrefix, Class<? extends IPSValueObject> entityClass, String fieldName) {
		return this.getJoinAlias(pPrefix, entityClass) + "." + fieldName;
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
