package de.mediapool.web.client;

import org.apache.log4j.Logger;

import com.vaadin.data.util.BeanContainer;

import de.mediapool.web.client.data.MediaContainer;
import entity.Media;
import entity.Movie;

public class MediaManagerService {

	static Logger log = Logger.getLogger(MediaManagerService.class);

	public static BeanContainer<Long, Movie> getMovieContainer() {
		BeanContainer<Long, Movie> container = new BeanContainer<Long, Movie>(Movie.class);
		container.setBeanIdProperty("id");
		for (Movie entity : Movie.findAllMovies()) {
			container.addBean(entity);
		}
		return container;
	}

	public static MediaContainer getMediaContainer() {
		MediaContainer container = null;
		try {
			container = new MediaContainer();
			container.setBeanIdProperty("id");
			for (Media entity : Media.findAllMedias()) {
				container.addBean(entity);
			}
		} catch (InstantiationException e) {
			log.error(e.toString());
		} catch (IllegalAccessException e) {
			log.error(e.toString());
		}
		return container;
	}
}
