package entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import entity.meta.Participation;
import entity.select.Award;
import entity.select.MLanguage;

@Configurable
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {

	private String title;

	private String originaltitle;

	private String launchyear;

	private String cover;

	private double price;

	private String ean;

	private String description;

	private String special;

	private String contenttype;

	private String genre;

	private String carrier;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<MLanguage> mlanguage = new HashSet<MLanguage>();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Award> award = new HashSet<Award>();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Participation> participation = new HashSet<Participation>();

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

	public String getLaunchyear() {
        return this.launchyear;
    }

	public void setLaunchyear(String launchyear) {
        this.launchyear = launchyear;
    }

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

	public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public String getSpecial() {
        return this.special;
    }

	public void setSpecial(String special) {
        this.special = special;
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

	public String getCarrier() {
        return this.carrier;
    }

	public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

	public Set<MLanguage> getMlanguage() {
        return this.mlanguage;
    }

	public void setMlanguage(Set<MLanguage> mlanguage) {
        this.mlanguage = mlanguage;
    }

	public Set<Award> getAward() {
        return this.award;
    }

	public void setAward(Set<Award> award) {
        this.award = award;
    }

	public Set<Participation> getParticipation() {
        return this.participation;
    }

	public void setParticipation(Set<Participation> participation) {
        this.participation = participation;
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Award: ").append(getAward() == null ? "null" : getAward().size()).append(", ");
        sb.append("Carrier: ").append(getCarrier()).append(", ");
        sb.append("Contenttype: ").append(getContenttype()).append(", ");
        sb.append("Cover: ").append(getCover()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Ean: ").append(getEan()).append(", ");
        sb.append("Genre: ").append(getGenre()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Launchyear: ").append(getLaunchyear()).append(", ");
        sb.append("Mlanguage: ").append(getMlanguage() == null ? "null" : getMlanguage().size()).append(", ");
        sb.append("Originaltitle: ").append(getOriginaltitle()).append(", ");
        sb.append("Participation: ").append(getParticipation() == null ? "null" : getParticipation().size()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("Special: ").append(getSpecial()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

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

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Media attached = Media.findMedia(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public Media merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Media merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new Media().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMedias() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Media o", Long.class).getSingleResult();
    }

	public static List<Media> findAllMedias() {
        return entityManager().createQuery("SELECT o FROM Media o", Media.class).getResultList();
    }

	public static Media findMedia(Long id) {
        if (id == null) return null;
        return entityManager().find(Media.class, id);
    }

	public static List<Media> findMediaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Media o", Media.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
