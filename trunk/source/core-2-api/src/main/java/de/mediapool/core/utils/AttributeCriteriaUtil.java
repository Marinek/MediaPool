package de.mediapool.core.utils;

import de.mediapool.core.beans.search.AbstractCriteriaBean;
import de.mediapool.core.beans.search.SearchOperation;
import de.mediapool.core.beans.search.criteria.AttributeCriteriaBean;

public class AttributeCriteriaUtil {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public static AbstractCriteriaBean eq(String pAttributeName, String pValue) {
		return new AttributeCriteriaBean(SearchOperation.EQ, pAttributeName, pValue);
	}

	public static AbstractCriteriaBean between(String pAttributeName, String pValueFrom, String pValueTo) {
		return new AttributeCriteriaBean(SearchOperation.BETWEEN, pAttributeName, pValueFrom, pValueTo);
	}

	public static AbstractCriteriaBean in(String pAttributeName, String... pValue) {
		return new AttributeCriteriaBean(SearchOperation.IN, pAttributeName, pValue);
	}

	public static AbstractCriteriaBean like(String pAttributeName, String pValue) {
		return new AttributeCriteriaBean(SearchOperation.LIKE, pAttributeName, pValue);
	}

	public static AbstractCriteriaBean lt(String pAttributeName, String pValue) {
		return new AttributeCriteriaBean(SearchOperation.LT, pAttributeName, pValue);
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
