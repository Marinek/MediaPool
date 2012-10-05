package de.mediapool.core.domain.container;

public enum MovieEntryType {
	MOVIEENTRY(1), MOVIEPRODUCTENTRY(2), MOVIEHOLDINGENTRY(3);

	private int id;

	private MovieEntryType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
