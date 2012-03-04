package de.mediapool.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class MRating implements Serializable {

	private double voting;

	private String description;

	@ManyToOne(cascade = CascadeType.MERGE)
	private MUser muser;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Media media;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ratingDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version")
	private Integer version;

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

	public double getVoting() {
		return voting;
	}

	public void setVoting(double voting) {
		this.voting = voting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MUser getMuser() {
		return muser;
	}

	public void setMuser(MUser muser) {
		this.muser = muser;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Date getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}

}
