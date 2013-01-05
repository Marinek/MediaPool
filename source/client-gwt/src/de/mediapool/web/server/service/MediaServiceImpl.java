package de.mediapool.web.server.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.mediapool.core.services.interfaces.IMovieService;
import de.mediapool.web.client.MediaService;
import de.mediapool.web.client.dto.Movie;
import de.mediapool.web.server.core.MovieService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MediaServiceImpl extends RemoteServiceServlet implements MediaService {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

	public IMovieService movieService;

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	@Override
	public List<Movie> searchMedia(Movie movie) throws IllegalArgumentException {
		return MovieService.searchMedia(movie);
	}

	@Override
	public List<Movie> createMedia(Movie movie) throws IllegalArgumentException {
		return MovieService.createMedia(movie);
	}

	@Override
	public List<Movie> deleteMedia(Movie movie) throws IllegalArgumentException {
		return MovieService.deleteMedia(movie);

	}

	@Override
	public List<Movie> updateMedia(Movie movie) throws IllegalArgumentException {
		return MovieService.updateMedia(movie);
	}

}
