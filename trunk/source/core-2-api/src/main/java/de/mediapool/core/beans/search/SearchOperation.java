package de.mediapool.core.beans.search;

public enum SearchOperation {

	GT(">", 1, 1),
	LT("<", 1, 1),
	EQ("=", 1, 1),
	IN("in", 0, Integer.MAX_VALUE),
	BETWEEN("..", 2, 2),
	LIKE("like", 1, 1);
	
	private String operator;
	
	private int minParams = 0;
	private int maxParams = 0;
	

	private SearchOperation(String pOperator, int minParams, int maxParams) {
		this.operator = pOperator;
		this.minParams = minParams;
		this.maxParams = maxParams;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public String toString() {
		return this.getOperator();
	}

	public int getMaxParams() {
		return maxParams;
	}

	public int getMinParams() {
		return minParams;
	}

}
