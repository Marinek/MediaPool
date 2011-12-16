package de.mediapool.persistence.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import de.mediapool.persistence.core.interfaces.IValueObject;
 
@Entity()
@Table(name = "Movies")
public class MovieVO extends MediaVO implements IValueObject
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
}
