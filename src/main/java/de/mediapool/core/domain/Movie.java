package de.mediapool.core.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Movie extends Media {

	public Movie() {

	}

	public Movie(Participation participation) {
		Set<Participation> participations = new HashSet<Participation>();
		participations.add(participation);
		setParticipation(participations);
	}

	private String productionland;
	private String studio;

	public String getProductionland() {
		return productionland;
	}

	public void setProductionland(String productionland) {
		this.productionland = productionland;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contenttype: ").append(getContenttype()).append(", ");
		sb.append("Cover: ").append(getCover()).append(", ");
		sb.append("Description: ").append(getDescription()).append(", ");
		sb.append("Genre: ").append(getGenre()).append(", ");
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Launchyear: ").append(getLaunchyear()).append(", ");
		sb.append("Mlanguage: ").append(getMlanguage() == null ? "null" : getMlanguage()).append(", ");
		sb.append("Originaltitle: ").append(getOriginaltitle()).append(", ");
		sb.append("Participation: ").append(getParticipation() == null ? "null" : getParticipation().size())
				.append(", ");
		sb.append("Title: ").append(getTitle()).append(", ");
		sb.append("Version: ").append(getVersion());
		return sb.toString();
	}

}
