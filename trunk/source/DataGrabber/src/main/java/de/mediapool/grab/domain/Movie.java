package de.mediapool.grab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Movie {

	private String title;

	private String originaltitle;

	private String mlanguage;

	private String productionsland;

	private Integer mage;

	private Integer myear;

	private Integer mlenght;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Cooperation> cooperation = new HashSet<Cooperation>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginaltitle() {
		return originaltitle;
	}

	public void setOriginaltitle(String originaltitle) {
		this.originaltitle = originaltitle;
	}

	public String getMlanguage() {
		return mlanguage;
	}

	public void setMlanguage(String mlanguage) {
		this.mlanguage = mlanguage;
	}

	public String getProductionsland() {
		return productionsland;
	}

	public void setProductionsland(String productionsland) {
		this.productionsland = productionsland;
	}

	public Integer getMage() {
		return mage;
	}

	public void setMage(Integer mage) {
		this.mage = mage;
	}

	public Integer getMyear() {
		return myear;
	}

	public void setMyear(Integer myear) {
		this.myear = myear;
	}

	public Integer getMlenght() {
		return mlenght;
	}

	public void setMlenght(Integer mlenght) {
		this.mlenght = mlenght;
	}

	public Set<Cooperation> getCooperation() {
		return cooperation;
	}

	public void setCooperation(Set<Cooperation> cooperation) {
		this.cooperation = cooperation;
	}
}
