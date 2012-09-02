package de.mediapool.core.beans.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mediapool.core.beans.AbstractBean;

public abstract class AbstractSearchBean<C extends AbstractCriteriaBean> extends AbstractBean {

	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private Map<String,List<C>> criteriaCollectionMap = new HashMap<String, List<C>>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	public void addCriteria (C pCriteria) {
		this.addCriteria("", pCriteria);
	}
	
	public void addCriteria (String prefix, C pCriteria) {
		
		if(!this.criteriaCollectionMap.containsKey(prefix)) {
			this.criteriaCollectionMap.put(prefix, new ArrayList<C>());
		}
		
		this.criteriaCollectionMap.get(prefix).add(pCriteria);
	}
	
	public List<C> getCriteriaList() {
		return this.getCriteriaList("");
	}
	
	public List<C> getCriteriaList(String prefix) {
		if(!this.criteriaCollectionMap.containsKey(prefix))	 {
			this.criteriaCollectionMap.put(prefix, Collections.<C> emptyList());
		}
		return Collections.unmodifiableList(this.criteriaCollectionMap.get(prefix));
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
