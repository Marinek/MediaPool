package de.mediapool.core.beans.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.mediapool.core.beans.AbstractBean;

public abstract class AbstractCriteriaBean extends AbstractBean {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private SearchOperation operation = null;

	private List<String> values = new ArrayList<String>();

	private String field = null;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected AbstractCriteriaBean(SearchOperation pOperation, String pField, String... pValues) {
		operation = pOperation;
		values = Arrays.asList(pValues);
		this.field = pField;

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

	public List<String> getValues() {
		return Collections.unmodifiableList(this.values);
	}

	public String getSingleValue() {
		return this.values.get(0);
	}

	public String getField() {
		return this.field;
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
