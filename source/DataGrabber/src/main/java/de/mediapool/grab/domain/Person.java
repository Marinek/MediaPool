package de.mediapool.grab.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Person {

	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}
}
