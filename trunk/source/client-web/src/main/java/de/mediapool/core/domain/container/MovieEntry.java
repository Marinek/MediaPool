package de.mediapool.core.domain.container;

import java.io.Serializable;
import java.util.Set;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Participation;

@SuppressWarnings("serial")
public class MovieEntry implements Serializable, MediaInterface {
	private Movie movie;

	public static final MovieEntryType entryType = MovieEntryType.MOVIEENTRY;

	public MovieEntry() {

	}

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Genre" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "title", "genre" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "title", "genre", "launchyear", "duration", "approvedage", "description" };
	}

	@Override
	public boolean isReadOnly() {
		return true;
	}

	public MovieEntry(Movie movie) {
		setMovie(movie);
	}

	public String getProductionland() {
		return getMovie().getProductionland();
	}

	public void setProductionland(String productionland) {
		getMovie().setProductionland(productionland);
	}

	public String getTitle() {
		return getMovie().getTitle();
	}

	public void setTitle(String title) {
		getMovie().setTitle(title);
	}

	public String getOriginaltitle() {
		return getMovie().getOriginaltitle();
	}

	public void setOriginaltitle(String originaltitle) {
		getMovie().setOriginaltitle(originaltitle);
	}

	public int getLaunchyear() {
		return getMovie().getLaunchyear();
	}

	public void setLaunchyear(int launchyear) {
		getMovie().setLaunchyear(launchyear);
	}

	public String getCover() {
		return getMovie().getCover();
	}

	public void setCover(String cover) {
		getMovie().setCover(cover);
	}

	public String getDescription() {
		return getMovie().getDescription();
	}

	public void setDescription(String description) {
		getMovie().setDescription(description);
	}

	public void setContenttype(String contenttype) {
		getMovie().setContenttype(contenttype);
	}

	public String getGenre() {
		return getMovie().getGenre();
	}

	public void setGenre(String genre) {
		getMovie().setGenre(genre);
	}

	public Set<Participation> getParticipation() {
		return getMovie().getParticipation();
	}

	public void setParticipation(Set<Participation> participation) {
		getMovie().setParticipation(participation);
	}

	public String getMlanguage() {
		return getMovie().getMlanguage();
	}

	public void setMlanguage(String mlanguage) {
		getMovie().setMlanguage(mlanguage);
	}

	public String getMediatype() {
		return getMovie().getMediatype();
	}

	public void setMediatype(String mediatype) {
		getMovie().setMediatype(mediatype);
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public boolean isLocal() {
		return movie.isLocal();
	}

	public void setLocal(boolean local) {
		movie.setLocal(local);
	}

}
