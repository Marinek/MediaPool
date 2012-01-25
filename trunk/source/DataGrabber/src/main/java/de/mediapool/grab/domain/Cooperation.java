package de.mediapool.grab.domain;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Cooperation {

	private String part;

	@ManyToOne(cascade = CascadeType.ALL)
	private Person person;

	public Cooperation() {

	}

	public Cooperation(String part, Person person) {
		super();
		this.part = part;
		this.person = person;
	}
}
