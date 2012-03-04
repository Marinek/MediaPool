package de.mediapool.core.beans.media;


public class MovieBean extends AbstractMediaBean {

	private int length;

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public MediaType getMediaType() {
		return MediaType.MOVIE;
	}
}
