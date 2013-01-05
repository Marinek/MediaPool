package entity;

import java.util.List;
import javax.persistence.Entity;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class Game extends Media {

    private int extension;

    private int playerto;

    private int approvedage;

    private String modus;

    private String requirement;

    private Boolean euipmentneeded;

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Approvedage: ").append(getApprovedage()).append(", ");
        sb.append("Award: ").append(getAward() == null ? "null" : getAward().size()).append(", ");
        sb.append("Carrier: ").append(getCarrier()).append(", ");
        sb.append("Contenttype: ").append(getContenttype()).append(", ");
        sb.append("Cover: ").append(getCover()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Ean: ").append(getEan()).append(", ");
        sb.append("Euipmentneeded: ").append(getEuipmentneeded()).append(", ");
        sb.append("Extension: ").append(getExtension()).append(", ");
        sb.append("Genre: ").append(getGenre()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Launchyear: ").append(getLaunchyear()).append(", ");
        sb.append("Mlanguage: ").append(getMlanguage() == null ? "null" : getMlanguage().size()).append(", ");
        sb.append("Modus: ").append(getModus()).append(", ");
        sb.append("Originaltitle: ").append(getOriginaltitle()).append(", ");
        sb.append("Participation: ").append(getParticipation() == null ? "null" : getParticipation().size()).append(", ");
        sb.append("Playerto: ").append(getPlayerto()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("Requirement: ").append(getRequirement()).append(", ");
        sb.append("Special: ").append(getSpecial()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	public int getExtension() {
        return this.extension;
    }

	public void setExtension(int extension) {
        this.extension = extension;
    }

	public int getPlayerto() {
        return this.playerto;
    }

	public void setPlayerto(int playerto) {
        this.playerto = playerto;
    }

	public int getApprovedage() {
        return this.approvedage;
    }

	public void setApprovedage(int approvedage) {
        this.approvedage = approvedage;
    }

	public String getModus() {
        return this.modus;
    }

	public void setModus(String modus) {
        this.modus = modus;
    }

	public String getRequirement() {
        return this.requirement;
    }

	public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

	public Boolean getEuipmentneeded() {
        return this.euipmentneeded;
    }

	public void setEuipmentneeded(Boolean euipmentneeded) {
        this.euipmentneeded = euipmentneeded;
    }

	public static long countGames() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Game o", Long.class).getSingleResult();
    }

	public static List<Game> findAllGames() {
        return entityManager().createQuery("SELECT o FROM Game o", Game.class).getResultList();
    }

	public static Game findGame(Long id) {
        if (id == null) return null;
        return entityManager().find(Game.class, id);
    }

	public static List<Game> findGameEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Game o", Game.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
