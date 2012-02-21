package de.mediapool.core;

public interface MediaInterface {
	public String[] header_names();

	public Object[] header_order();

	public Object[] form_fields();

	public boolean isReadOnly();

}
