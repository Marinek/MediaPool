package entity.select;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Configurable
public class Contenttype {

    private String title;

    private String mptype;

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
            Contenttype attached = Contenttype.findContenttype(this.id);
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
    public Contenttype merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Contenttype merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new Contenttype().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countContenttypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Contenttype o", Long.class).getSingleResult();
    }

	public static List<Contenttype> findAllContenttypes() {
        return entityManager().createQuery("SELECT o FROM Contenttype o", Contenttype.class).getResultList();
    }

	public static Contenttype findContenttype(Long id) {
        if (id == null) return null;
        return entityManager().find(Contenttype.class, id);
    }

	public static List<Contenttype> findContenttypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Contenttype o", Contenttype.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Mptype: ").append(getMptype()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	public String getTitle() {
        return this.title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public String getMptype() {
        return this.mptype;
    }

	public void setMptype(String mptype) {
        this.mptype = mptype;
    }
}
