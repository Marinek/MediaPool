package entity;

import java.util.List;
import javax.persistence.Entity;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Boardgame extends Media {

    private int duration;

    private int playerfrom;

    private int playerto;

    private int approvedage;

    private int extension;

	public static long countBoardgames() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Boardgame o", Long.class).getSingleResult();
    }

	public static List<Boardgame> findAllBoardgames() {
        return entityManager().createQuery("SELECT o FROM Boardgame o", Boardgame.class).getResultList();
    }

	public static Boardgame findBoardgame(Long id) {
        if (id == null) return null;
        return entityManager().find(Boardgame.class, id);
    }

	public static List<Boardgame> findBoardgameEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Boardgame o", Boardgame.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public int getDuration() {
        return this.duration;
    }

	public void setDuration(int duration) {
        this.duration = duration;
    }

	public int getPlayerfrom() {
        return this.playerfrom;
    }

	public void setPlayerfrom(int playerfrom) {
        this.playerfrom = playerfrom;
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

	public int getExtension() {
        return this.extension;
    }

	public void setExtension(int extension) {
        this.extension = extension;
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Approvedage: ").append(getApprovedage()).append(", ");
        sb.append("Award: ").append(getAward() == null ? "null" : getAward().size()).append(", ");
        sb.append("Carrier: ").append(getCarrier()).append(", ");
        sb.append("Contenttype: ").append(getContenttype()).append(", ");
        sb.append("Cover: ").append(getCover()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Duration: ").append(getDuration()).append(", ");
        sb.append("Ean: ").append(getEan()).append(", ");
        sb.append("Extension: ").append(getExtension()).append(", ");
        sb.append("Genre: ").append(getGenre()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Launchyear: ").append(getLaunchyear()).append(", ");
        sb.append("Mlanguage: ").append(getMlanguage() == null ? "null" : getMlanguage().size()).append(", ");
        sb.append("Originaltitle: ").append(getOriginaltitle()).append(", ");
        sb.append("Participation: ").append(getParticipation() == null ? "null" : getParticipation().size()).append(", ");
        sb.append("Playerfrom: ").append(getPlayerfrom()).append(", ");
        sb.append("Playerto: ").append(getPlayerto()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("Special: ").append(getSpecial()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
}
