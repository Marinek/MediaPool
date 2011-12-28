package de.mediapool.core.persistence.vo.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.IMovieDAO;
 
@Entity()
@Table(name = "Movies")
public class MovieVO extends MediaVO
{
	private static final long serialVersionUID = 1L;

	@Column(name="lengthMinutes")
	private int lengthMinutes;

	public void setLengthMinutes(int lengthMinutes) {
		this.lengthMinutes = lengthMinutes;
	}

	public int getLengthMinutes() {
		return lengthMinutes;
	}

	public static IMovieDAO getDAO() {
		return (IMovieDAO) PersistenceContext.getInstance().getDAO(IMovieDAO.class);
	}
}
