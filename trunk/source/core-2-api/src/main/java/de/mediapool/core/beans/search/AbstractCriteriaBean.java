package de.mediapool.core.beans.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.keyvalue.string.KeyValueBean;

public abstract class AbstractCriteriaBean extends AbstractBean {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private SearchOperation operation = null;

	private List<KeyValueBean> values = new ArrayList<KeyValueBean>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected AbstractCriteriaBean(SearchOperation pOperation, KeyValueBean... pValues) {
		operation = pOperation;
		values = Arrays.asList(pValues);

		this.check();
	}

	private void check() {
		if (this.values == null) {
			this.values = Collections.emptyList();
		}

		if (this.operation == null) {
			throw new IllegalArgumentException("Operationtype must not be null.");
		}

		if (this.values.size() < this.operation.getMinParams()) {
			throw new IllegalArgumentException("Not enough values for this Operation: " + this.operation);
		}

		if (this.values.size() > this.operation.getMaxParams()) {
			throw new IllegalArgumentException("Maximum values exeeded for this Operation: " + this.operation);
		}
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public SearchOperation getOperation() {
		return operation;
	}

	public List<KeyValueBean> getKeyValues() {
		return Collections.unmodifiableList(this.values);
	}

	public String getSingleValue() {
		return this.values.get(0).getValue();
	}

	public String getSingleKey() {
		return this.values.get(0).getKey();
	}

	public List<String> getValuesAsList() {
		List<String> listValues = new ArrayList<String>();

		for (KeyValueBean keyValueBean : this.values) {
			listValues.add(keyValueBean.getValue());
		}

		return listValues;
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
