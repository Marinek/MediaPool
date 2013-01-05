package entity.meta;

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
public class Track {

    private String title;

    private int tnumber;

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Tnumber: ").append(getTnumber()).append(", ");
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
            Track attached = Track.findTrack(this.id);
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
    public Track merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Track merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new Track().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countTracks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Track o", Long.class).getSingleResult();
    }

	public static List<Track> findAllTracks() {
        return entityManager().createQuery("SELECT o FROM Track o", Track.class).getResultList();
    }

	public static Track findTrack(Long id) {
        if (id == null) return null;
        return entityManager().find(Track.class, id);
    }

	public static List<Track> findTrackEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Track o", Track.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String getTitle() {
        return this.title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public int getTnumber() {
        return this.tnumber;
    }

	public void setTnumber(int tnumber) {
        this.tnumber = tnumber;
    }
}
