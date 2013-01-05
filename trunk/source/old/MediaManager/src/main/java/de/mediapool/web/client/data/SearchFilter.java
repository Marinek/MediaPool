package de.mediapool.web.client.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SearchFilter implements Serializable {

	private final String term;
	private final Object propertyId;
	private String searchName;
	private boolean ignoreCase;
	private boolean matchPrefix;

	public SearchFilter(Object propertyId, String searchTerm, String name) {
		this.propertyId = propertyId;
		this.term = searchTerm;
		this.searchName = name;
		this.ignoreCase = true;
		this.matchPrefix = false;

	}

	public SearchFilter(Object propertyId, String searchTerm, String name, boolean ignoreCase, boolean matchPrefix) {
		this.propertyId = propertyId;
		this.term = searchTerm;
		this.searchName = name;
		this.matchPrefix = matchPrefix;
		this.ignoreCase = ignoreCase;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @return the propertyId
	 */
	public Object getPropertyId() {
		return propertyId;
	}

	/**
	 * @return the name of the search
	 */
	public String getSearchName() {
		return searchName;
	}

	@Override
	public String toString() {
		return getSearchName();
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public boolean isMatchPrefix() {
		return matchPrefix;
	}

	public void setMatchPrefix(boolean matchPrefix) {
		this.matchPrefix = matchPrefix;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
