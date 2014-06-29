package models;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.db.jpa.Model;

@Entity
public class Car extends Model {
	public int platenumber;
	public String location;
	public String color;
	public String type;
	public String special;
	@Lob
	public byte[] image;
	public String imageMimeType;

}
