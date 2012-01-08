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

@Configurable
@Entity
public class Award {

    private String title;

    private String ayear;

    private String category;

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
            Award attached = Award.findAward(this.id);
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
    public Award merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Award merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new Award().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countAwards() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Award o", Long.class).getSingleResult();
    }

	public static List<Award> findAllAwards() {
        return entityManager().createQuery("SELECT o FROM Award o", Award.class).getResultList();
    }

	public static Award findAward(Long id) {
        if (id == null) return null;
        return entityManager().find(Award.class, id);
    }

	public static List<Award> findAwardEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Award o", Award.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String getTitle() {
        return this.title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public String getAyear() {
        return this.ayear;
    }

	public void setAyear(String ayear) {
        this.ayear = ayear;
    }

	public String getCategory() {
        return this.category;
    }

	public void setCategory(String category) {
        this.category = category;
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ayear: ").append(getAyear()).append(", ");
        sb.append("Category: ").append(getCategory()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
}
