package entity;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class Book extends Media {

	private int pages;

	private String isbn;

	public int getPages() {
		return this.pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public static long countBooks() {
		return entityManager().createQuery("SELECT COUNT(o) FROM Book o", Long.class).getSingleResult();
	}

	public static List<Book> findAllBooks() {
		return entityManager().createQuery("SELECT o FROM Book o", Book.class).getResultList();
	}

	public static Book findBook(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Book.class, id);
	}

	public static List<Book> findBookEntries(int firstResult, int maxResults) {
		return entityManager().createQuery("SELECT o FROM Book o", Book.class).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Award: ").append(getAward() == null ? "null" : getAward().size()).append(", ");
		sb.append("Carrier: ").append(getCarrier()).append(", ");
		sb.append("Contenttype: ").append(getContenttype()).append(", ");
		sb.append("Cover: ").append(getCover()).append(", ");
		sb.append("Description: ").append(getDescription()).append(", ");
		sb.append("Ean: ").append(getEan()).append(", ");
		sb.append("Genre: ").append(getGenre()).append(", ");
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Isbn: ").append(getIsbn()).append(", ");
		sb.append("Launchyear: ").append(getLaunchyear()).append(", ");
		sb.append("Mlanguage: ").append(getMlanguage() == null ? "null" : getMlanguage().size()).append(", ");
		sb.append("Originaltitle: ").append(getOriginaltitle()).append(", ");
		sb.append("Pages: ").append(getPages()).append(", ");
		sb.append("Participation: ").append(getParticipation() == null ? "null" : getParticipation().size())
				.append(", ");
		sb.append("Price: ").append(getPrice()).append(", ");
		sb.append("Special: ").append(getSpecial()).append(", ");
		sb.append("Title: ").append(getTitle()).append(", ");
		sb.append("Version: ").append(getVersion());
		return sb.toString();
	}
}
