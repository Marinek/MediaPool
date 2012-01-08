package entity.user;

import org.springframework.transaction.annotation.Transactional;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import entity.Media;
import javax.persistence.ManyToOne;
import entity.user.MUser;

@Entity
@Configurable
public class Holding {

    private String knowm;

    private String since;

    private String rating;

    private Boolean visible;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar lastUsed;

    private String situation;

    @ManyToOne
    private Media media;

    @ManyToOne
    private MUser muser;

    private String inventoryplace;

    private String inventorynumber;

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
            Holding attached = Holding.findHolding(this.id);
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
    public Holding merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Holding merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new Holding().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countHoldings() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Holding o", Long.class).getSingleResult();
    }

	public static List<Holding> findAllHoldings() {
        return entityManager().createQuery("SELECT o FROM Holding o", Holding.class).getResultList();
    }

	public static Holding findHolding(Long id) {
        if (id == null) return null;
        return entityManager().find(Holding.class, id);
    }

	public static List<Holding> findHoldingEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Holding o", Holding.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String getKnowm() {
        return this.knowm;
    }

	public void setKnowm(String knowm) {
        this.knowm = knowm;
    }

	public String getSince() {
        return this.since;
    }

	public void setSince(String since) {
        this.since = since;
    }

	public String getRating() {
        return this.rating;
    }

	public void setRating(String rating) {
        this.rating = rating;
    }

	public Boolean getVisible() {
        return this.visible;
    }

	public void setVisible(Boolean visible) {
        this.visible = visible;
    }

	public Calendar getLastUsed() {
        return this.lastUsed;
    }

	public void setLastUsed(Calendar lastUsed) {
        this.lastUsed = lastUsed;
    }

	public String getSituation() {
        return this.situation;
    }

	public void setSituation(String situation) {
        this.situation = situation;
    }

	public Media getMedia() {
        return this.media;
    }

	public void setMedia(Media media) {
        this.media = media;
    }

	public MUser getMuser() {
        return this.muser;
    }

	public void setMuser(MUser muser) {
        this.muser = muser;
    }

	public String getInventoryplace() {
        return this.inventoryplace;
    }

	public void setInventoryplace(String inventoryplace) {
        this.inventoryplace = inventoryplace;
    }

	public String getInventorynumber() {
        return this.inventorynumber;
    }

	public void setInventorynumber(String inventorynumber) {
        this.inventorynumber = inventorynumber;
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Inventorynumber: ").append(getInventorynumber()).append(", ");
        sb.append("Inventoryplace: ").append(getInventoryplace()).append(", ");
        sb.append("Knowm: ").append(getKnowm()).append(", ");
        sb.append("LastUsed: ").append(getLastUsed() == null ? "null" : getLastUsed().getTime()).append(", ");
        sb.append("Media: ").append(getMedia()).append(", ");
        sb.append("Muser: ").append(getMuser()).append(", ");
        sb.append("Rating: ").append(getRating()).append(", ");
        sb.append("Since: ").append(getSince()).append(", ");
        sb.append("Situation: ").append(getSituation()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Visible: ").append(getVisible());
        return sb.toString();
    }
}
