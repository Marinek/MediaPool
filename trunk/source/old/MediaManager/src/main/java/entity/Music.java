package entity;

import org.springframework.beans.factory.annotation.Configurable;
import java.util.List;
import java.util.Set;
import entity.meta.Track;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@Configurable
@Entity
public class Music extends Media {

    private int duration;

    private int numberdiscs;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Track> tracks = new HashSet<Track>();

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

	public Set<Track> getTracks() {
        return this.tracks;
    }

	public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
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
        sb.append("Tracks: ").append(getTracks() == null ? "null" : getTracks().size()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	public static long countMusics() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Music o", Long.class).getSingleResult();
    }

	public static List<Music> findAllMusics() {
        return entityManager().createQuery("SELECT o FROM Music o", Music.class).getResultList();
    }

	public static Music findMusic(Long id) {
        if (id == null) return null;
        return entityManager().find(Music.class, id);
    }

	public static List<Music> findMusicEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Music o", Music.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
