package de.mediapool.core.beans.search;

public enum SearchOperation {

	GT(">"),
	LT("<"),
	EQ("="),
	IN("in"),
	BETWEEN(".."),
	LIKE("like");
	
	private String operator;

	private SearchOperation(String pOperator) {
		this.operator = pOperator;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public String toString() {
		return this.getOperator();
	}
}
