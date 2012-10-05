package de.mediapool.core.domain.container;

import java.io.Serializable;
import java.util.Arrays;

import de.mediapool.core.domain.Product;

@SuppressWarnings("serial")
public class MovieProductEntry extends MovieEntry implements Serializable {
	private Product product;

	public MovieProductEntry() {

	}

	@Override
	public boolean isReadOnly() {
		return true;
	}

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Genre", "Medium" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "title", "genre", "carrier" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "special", "price", "carrier" };
	}

	public MovieProductEntry(Product product) {
		setProduct(product);
		setMovie(getProduct().getMovie());
	}

	public double getPrice() {
		return getProduct().getPrice();
	}

	public void setPrice(double price) {
		getProduct().setPrice(price);
	}

	public long getEan() {
		return getProduct().getEan();
	}

	public void setEan(long ean) {
		getProduct().setEan(ean);
	}

	public String getSpecial() {
		return getProduct().getSpecial();
	}

	public void setSpecial(String special) {
		getProduct().setSpecial(special);
	}

	public String getCarrier() {
		return getProduct().getCarrier();
	}

	public void setCarrier(String carrier) {
		getProduct().setCarrier(carrier);
	}

	public String getImage() {
		return getProduct().getImage();
	}

	public void setImage(String image) {
		getProduct().setImage(image);
	}

	public int getApprovedage() {
		return getProduct().getApprovedage();
	}

	public void setApprovedage(int approvedage) {
		getProduct().setApprovedage(approvedage);
	}

	public int getDuration() {
		return getProduct().getDuration();
	}

	public void setDuration(int duration) {
		getProduct().setDuration(duration);
	}

	public int getNumberdiscs() {
		return getProduct().getNumberdiscs();
	}

	public void setNumberdiscs(int numberdiscs) {
		getProduct().setNumberdiscs(numberdiscs);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "MovieProductEntry [product=" + product + ", isReadOnly()=" + isReadOnly() + ", header_names()="
				+ Arrays.toString(header_names()) + ", header_order()=" + Arrays.toString(header_order())
				+ ", form_fields()=" + Arrays.toString(form_fields()) + ", getPrice()=" + getPrice() + ", getEan()="
				+ getEan() + ", getSpecial()=" + getSpecial() + ", getCarrier()=" + getCarrier()
				+ ", getApprovedage()=" + getApprovedage() + ", getDuration()=" + getDuration() + ", getNumberdiscs()="
				+ getNumberdiscs() + ", getProduct()=" + getProduct() + ", getProductionland()=" + getProductionland()
				+ ", getTitle()=" + getTitle() + ", getOriginaltitle()=" + getOriginaltitle() + ", getLaunchyear()="
				+ getLaunchyear() + ", getCover()=" + getCover() + ", getDescription()=" + getDescription()
				+ ", getGenre()=" + getGenre() + ", getParticipation()=" + getParticipation() + ", getMlanguage()="
				+ getMlanguage() + ", getMediatype()=" + getMediatype() + ", getMovie()=" + getMovie()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
