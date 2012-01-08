package entity.user;

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
public class MUser {

    private String password;

    private String email;

    private String username;

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
            MUser attached = MUser.findMUser(this.id);
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
    public MUser merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new MUser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MUser o", Long.class).getSingleResult();
    }

	public static List<MUser> findAllMUsers() {
        return entityManager().createQuery("SELECT o FROM MUser o", MUser.class).getResultList();
    }

	public static MUser findMUser(Long id) {
        if (id == null) return null;
        return entityManager().find(MUser.class, id);
    }

	public static List<MUser> findMUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MUser o", MUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Password: ").append(getPassword()).append(", ");
        sb.append("Username: ").append(getUsername()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public String getEmail() {
        return this.email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public String getUsername() {
        return this.username;
    }

	public void setUsername(String username) {
        this.username = username;
    }
}
