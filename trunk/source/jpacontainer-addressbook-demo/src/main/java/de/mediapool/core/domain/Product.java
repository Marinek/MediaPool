package de.mediapool.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;


@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String cover;

	private double price;

	private String ean;

	private String special;

	private String carrier;

	private String mlanguage;

	private int approvedage;

	private int duration;

	private int numberdiscs;

	@ManyToOne(cascade = CascadeType.ALL)
	private Movie movie;

	@Version
	@Column(name = "version")
	private Integer version;

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getEan() {
		return this.ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getSpecial() {
		return this.special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getMlanguage() {
		return mlanguage;
	}

	public void setMlanguage(String mlanguage) {
		this.mlanguage = mlanguage;
	}

	public int getApprovedage() {
		return approvedage;
	}

	public void setApprovedage(int approvedage) {
		this.approvedage = approvedage;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getNumberdiscs() {
		return numberdiscs;
	}

	public void setNumberdiscs(int numberdiscs) {
		this.numberdiscs = numberdiscs;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Carrier: ").append(getCarrier()).append(", ");
		sb.append("Cover: ").append(getCover()).append(", ");
		sb.append("Ean: ").append(getEan()).append(", ");
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Price: ").append(getPrice()).append(", ");
		sb.append("Special: ").append(getSpecial()).append(", ");
		sb.append("Version: ").append(getVersion());
		return sb.toString();
	}

}
