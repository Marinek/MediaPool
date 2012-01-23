package com.vaadin.demo.jpaaddressbook.ui;

import com.vaadin.addon.jpacontainer.JPAContainer;

public class MovieContainer<Movie> extends JPAContainer<Movie> {

	private static final long serialVersionUID = 1L;

	public MovieContainer(Class<Movie> entityClass) {
		super(entityClass);
	}

}
