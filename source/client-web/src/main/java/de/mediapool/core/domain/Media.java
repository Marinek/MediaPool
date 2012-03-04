package de.mediapool.core.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import de.mediapool.core.MediaInterface;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Media implements Serializable, MediaInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String title;

	private String originaltitle;

	@Column(length = 10000)
	private String description;

	private String mediatype;

	private String contenttype;

	private String genre;

	private int launchyear;

	private String cover;

	private String mlanguage;

	private int duration;

	private int approvedage;

	private boolean local;

	@Transient
	private double averageRating;

	@Version
	@Column(name = "version")
	private Integer version;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Participation> participation = new HashSet<Participation>();

	@OneToMany(cascade = CascadeType.ALL)
	private Set<MRating> mratings = new HashSet<MRating>();

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Medium", "Inhalt", "Genre" };
	}

	@Override
	public Object[] header_order() {
		return new String[] { "title", "mediatype", "contenttype", "genre" };
	}

	@Override
	public Object[] form_fields() {
		return new String[] { "title", "mediatype", "contenttype", "genre" };
	}

	@Override
	public boolean isReadOnly() {
		return true;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginaltitle() {
		return this.originaltitle;
	}

	public void setOriginaltitle(String originaltitle) {
		this.originaltitle = originaltitle;
	}

	public int getLaunchyear() {
		return this.launchyear;
	}

	public void setLaunchyear(int launchyear) {
		this.launchyear = launchyear;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<Participation> getParticipation() {
		return this.participation;
	}

	public void setParticipation(Set<Participation> participation) {
		this.participation = participation;
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

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contenttype: ").append(getContenttype()).append(", ");
		sb.append("Cover: ").append(getCover()).append(", ");
		sb.append("Description: ").append(getDescription()).append(", ");
		sb.append("Genre: ").append(getGenre()).append(", ");
		sb.append("Originaltitle: ").append(getOriginaltitle()).append(", ");
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Launchyear: ").append(getLaunchyear()).append(", ");
		sb.append("Participation: ").append(getParticipation() == null ? "null" : getParticipation().size())
				.append(", ");
		sb.append("Title: ").append(getTitle()).append(", ");
		sb.append("Version: ").append(getVersion());
		return sb.toString();
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getApprovedage() {
		return approvedage;
	}

	public void setApprovedage(int approvedage) {
		this.approvedage = approvedage;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public Set<MRating> getMratings() {
		return mratings;
	}

	public void setMratings(Set<MRating> mratings) {
		this.mratings = mratings;
	}

}
