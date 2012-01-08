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

@Configurable
@Entity
public class PMember {

    private String firstname;

    private String name;

    private String type;

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Firstname: ").append(getFirstname()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Type: ").append(getType()).append(", ");
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
            PMember attached = PMember.findPMember(this.id);
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
    public PMember merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PMember merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new PMember().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPMembers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PMember o", Long.class).getSingleResult();
    }

	public static List<PMember> findAllPMembers() {
        return entityManager().createQuery("SELECT o FROM PMember o", PMember.class).getResultList();
    }

	public static PMember findPMember(Long id) {
        if (id == null) return null;
        return entityManager().find(PMember.class, id);
    }

	public static List<PMember> findPMemberEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PMember o", PMember.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String getFirstname() {
        return this.firstname;
    }

	public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getType() {
        return this.type;
    }

	public void setType(String type) {
        this.type = type;
    }
}
