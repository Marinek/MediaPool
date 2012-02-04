package de.mediapool.core.domain.container;

import java.io.Serializable;
import java.util.Set;

import de.mediapool.core.domain.MediaInterface;
import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Participation;
import de.mediapool.core.domain.Product;

@SuppressWarnings("serial")
public class MovieProductEntry implements Serializable, MediaInterface {
	private Movie movie;
	private Product product;

	public MovieProductEntry() {

	}

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Medium", "Wertung" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "title", "carrier", "rating" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "title", "carrier", "rating" };
	}

	public MovieProductEntry(Product product) {
		this.product = product;
		this.movie = product.getMovie();
	}

	public String getProductionland() {
		return movie.getProductionland();
	}

	public void setProductionland(String productionland) {
		movie.setProductionland(productionland);
	}

	public String getTitle() {
		return movie.getTitle();
	}

	public void setTitle(String title) {
		movie.setTitle(title);
	}

	public String getOriginaltitle() {
		return movie.getOriginaltitle();
	}

	public void setOriginaltitle(String originaltitle) {
		movie.setOriginaltitle(originaltitle);
	}

	public int getLaunchyear() {
		return movie.getLaunchyear();
	}

	public void setLaunchyear(int launchyear) {
		movie.setLaunchyear(launchyear);
	}

	public String getCover() {
		return movie.getCover();
	}

	public void setCover(String cover) {
		movie.setCover(cover);
	}

	public String getDescription() {
		return movie.getDescription();
	}

	public void setDescription(String description) {
		movie.setDescription(description);
	}

	public void setContenttype(String contenttype) {
		movie.setContenttype(contenttype);
	}

	public String getGenre() {
		return movie.getGenre();
	}

	public void setGenre(String genre) {
		movie.setGenre(genre);
	}

	public Set<Participation> getParticipation() {
		return movie.getParticipation();
	}

	public void setParticipation(Set<Participation> participation) {
		movie.setParticipation(participation);
	}

	public String getMlanguage() {
		return movie.getMlanguage();
	}

	public void setMlanguage(String mlanguage) {
		movie.setMlanguage(mlanguage);
	}

	public String getMediatype() {
		return movie.getMediatype();
	}

	public void setMediatype(String mediatype) {
		movie.setMediatype(mediatype);
	}

	public double getPrice() {
		return product.getPrice();
	}

	public void setPrice(double price) {
		product.setPrice(price);
	}

	public String getEan() {
		return product.getEan();
	}

	public void setEan(String ean) {
		product.setEan(ean);
	}

	public String getSpecial() {
		return product.getSpecial();
	}

	public void setSpecial(String special) {
		product.setSpecial(special);
	}

	public String getCarrier() {
		return product.getCarrier();
	}

	public void setCarrier(String carrier) {
		product.setCarrier(carrier);
	}

	public int getApprovedage() {
		return product.getApprovedage();
	}

	public void setApprovedage(int approvedage) {
		product.setApprovedage(approvedage);
	}

	public int getDuration() {
		return product.getDuration();
	}

	public void setDuration(int duration) {
		product.setDuration(duration);
	}

	public int getNumberdiscs() {
		return product.getNumberdiscs();
	}

	public void setNumberdiscs(int numberdiscs) {
		product.setNumberdiscs(numberdiscs);
	}

	public void setMovie(Movie movie) {
		product.setMovie(movie);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Movie getMovie() {
		return movie;
	}

}
