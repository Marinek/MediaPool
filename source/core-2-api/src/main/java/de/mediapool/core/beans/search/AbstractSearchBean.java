package de.mediapool.core.beans.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mediapool.core.beans.AbstractBean;

public abstract class AbstractSearchBean extends AbstractBean {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private Map<String, List<AbstractCriteriaBean>> criteriaCollectionMap = new HashMap<String, List<AbstractCriteriaBean>>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public void addCriteria(AbstractCriteriaBean pCriteria) {
		this.addCriteria("", pCriteria);
	}

	public void addCriteria(String prefix, AbstractCriteriaBean pCriteria) {

		if (!this.criteriaCollectionMap.containsKey(prefix)) {
			this.criteriaCollectionMap.put(prefix, new ArrayList<AbstractCriteriaBean>());
		}

		this.criteriaCollectionMap.get(prefix).add(pCriteria);
	}

	public List<AbstractCriteriaBean> getCriteriaList() {
		return this.getCriteriaList("");
	}

	public List<AbstractCriteriaBean> getCriteriaList(String prefix) {
		if (!this.criteriaCollectionMap.containsKey(prefix)) {
			this.criteriaCollectionMap.put(prefix, Collections.<AbstractCriteriaBean> emptyList());
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
