package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import entity.meta.Participation;
import entity.select.Award;
import entity.select.MLanguage;

@RooJavaBean
@RooToString
@RooEntity
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {

	private String title;

	private String originaltitle;

	private String launchyear;

	private String cover;

	private double price;

	private String ean;

	private String description;

	private String special;

	private String contenttype;

	private String genre;

	private String carrier;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<MLanguage> mlanguage = new HashSet<MLanguage>();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Award> award = new HashSet<Award>();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Participation> participation = new HashSet<Participation>();
}
