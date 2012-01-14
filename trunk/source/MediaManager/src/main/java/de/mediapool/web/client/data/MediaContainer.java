package de.mediapool.web.client.data;

import java.io.Serializable;

import com.vaadin.data.util.BeanContainer;

import entity.Media;

public class MediaContainer extends BeanContainer<Long, Media> implements Serializable {

	private static final long serialVersionUID = 1L;

	public MediaContainer() throws InstantiationException, IllegalAccessException {
		super(Media.class);
	}

}
