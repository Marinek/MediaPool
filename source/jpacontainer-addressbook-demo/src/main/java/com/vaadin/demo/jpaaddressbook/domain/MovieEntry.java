package com.vaadin.demo.jpaaddressbook.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@SuppressWarnings("serial")
public class MovieEntry implements Serializable {
	private Movie movie;
	private Product product;
	private MUser muser;
	private Holding holding;

	public MovieEntry() {

	}

	public MovieEntry(Holding holding) {
		this.product = holding.getProduct();
		this.movie = product.getMovie();
		this.muser = holding.getMuser();
		this.holding = holding;
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

	public String getLaunchyear() {
		return movie.getLaunchyear();
	}

	public void setLaunchyear(String launchyear) {
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

	public String getUsername() {
		return muser.getUsername();
	}

	public void setUsername(String username) {
		muser.setUsername(username);
	}

	public void setVersion(Integer version) {
		holding.setVersion(version);
	}

	public String getKnowm() {
		return holding.getKnowm();
	}

	public void setKnowm(String knowm) {
		holding.setKnowm(knowm);
	}

	public String getSince() {
		return holding.getSince();
	}

	public void setSince(String since) {
		holding.setSince(since);
	}

	public String getRating() {
		return holding.getRating();
	}

	public void setRating(String rating) {
		holding.setRating(rating);
	}

	public Boolean getVisible() {
		return holding.getVisible();
	}

	public void setVisible(Boolean visible) {
		holding.setVisible(visible);
	}

	public String getSituation() {
		return holding.getSituation();
	}

	public void setSituation(String situation) {
		holding.setSituation(situation);
	}

	public String getInventoryplace() {
		return holding.getInventoryplace();
	}

	public void setInventoryplace(String inventoryplace) {
		holding.setInventoryplace(inventoryplace);
	}

	public String getInventorynumber() {
		return holding.getInventorynumber();
	}

	public void setInventorynumber(String inventorynumber) {
		holding.setInventorynumber(inventorynumber);
	}

	public Date getLastUsed() {
		return holding.getLastUsed();
	}

	public void setLastUsed(Date lastUsed) {
		holding.setLastUsed(lastUsed);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public MUser getMuser() {
		return muser;
	}

	public void setMuser(MUser muser) {
		this.muser = muser;
	}

	public Holding getHolding() {
		return holding;
	}

	public void setHolding(Holding holding) {
		this.holding = holding;
	}

	public Movie getMovie() {
		return movie;
	}
}
