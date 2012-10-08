package de.mediapool.core.domain.migration;

import de.mediapool.core.MediaInterface;

//@Entity
//@Table(name = "Filme")
public class Filme implements MediaInterface {

	public Filme() {

	}

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "id")
	private Long id;

	private String name;
	private int fach;
	private String gesehen;
	private String medium;
	private String quali;
	private String wertung;
	private String genre;

	@Override
	public boolean isReadOnly() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFach() {
		return fach;
	}

	public void setFach(int fach) {
		this.fach = fach;
	}

	public String getGesehen() {
		return gesehen;
	}

	public void setGesehen(String gesehen) {
		this.gesehen = gesehen;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getQuali() {
		return quali;
	}

	public void setQuali(String quali) {
		this.quali = quali;
	}

	public String getWertung() {
		return wertung;
	}

	public void setWertung(String wertung) {
		this.wertung = wertung;
	}

	@Override
	public String[] header_names() {
		return new String[] { "name", "fach", "gesehen", "medium", "quali", "wertung" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "name", "fach", "gesehen", "medium", "quali", "wertung" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "name", "fach", "gesehen", "medium", "quali", "wertung" };
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
