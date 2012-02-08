package de.mediapool.core.domain.container;

import java.io.Serializable;

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
		return new Object[] { "title", "genre", "carrier" };
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

	public String getEan() {
		return getProduct().getEan();
	}

	public void setEan(String ean) {
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

}
