package entity;

import java.util.List;
import javax.persistence.Entity;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class Movie extends Media {

    private int duration;

    private int numberdiscs;

    private int approvedage;

	public int getDuration() {
        return this.duration;
    }

	public void setDuration(int duration) {
        this.duration = duration;
    }

	public int getNumberdiscs() {
        return this.numberdiscs;
    }

	public void setNumberdiscs(int numberdiscs) {
        this.numberdiscs = numberdiscs;
    }

	public int getApprovedage() {
        return this.approvedage;
    }

	public void setApprovedage(int approvedage) {
        this.approvedage = approvedage;
    }

	public static long countMovies() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Movie o", Long.class).getSingleResult();
    }

	public static List<Movie> findAllMovies() {
        return entityManager().createQuery("SELECT o FROM Movie o", Movie.class).getResultList();
    }

	public static Movie findMovie(Long id) {
        if (id == null) return null;
        return entityManager().find(Movie.class, id);
    }

	public static List<Movie> findMovieEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Movie o", Movie.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
        sb.append("Genre: ").append(getGenre()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Launchyear: ").append(getLaunchyear()).append(", ");
        sb.append("Mlanguage: ").append(getMlanguage() == null ? "null" : getMlanguage().size()).append(", ");
        sb.append("Numberdiscs: ").append(getNumberdiscs()).append(", ");
        sb.append("Originaltitle: ").append(getOriginaltitle()).append(", ");
        sb.append("Participation: ").append(getParticipation() == null ? "null" : getParticipation().size()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("Special: ").append(getSpecial()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
}
