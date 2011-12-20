package de.mediapool.web.server.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.mediapool.core.services.interfaces.IMovieService;
import de.mediapool.web.client.MediaService;
import de.mediapool.web.client.dto.Movie;
import de.mediapool.web.server.core.MovieService;
import de.mediapool.web.shared.FieldVerifier;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MediaServiceImpl extends RemoteServiceServlet implements
		MediaService {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"spring.xml");

	public IMovieService movieService;

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public List<Movie> getAllMovies() throws IllegalArgumentException {
		// Verify that the input is valid.
		String input = "test";

		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		String name = "";
		// name = name + getMovie();
		return MovieService.getallMovies();
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	// public String getMovie()
	// {
	// MovieBean media = null;
	//
	// try {
	// media = getMedia(1);
	// } catch (MPExeption e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return media != null ? media.getName() : "No Media found";
	// }
	//
	// @Override
	// public MovieBean getMedia(int mediaId) throws MPExeption {
	// MovieServiceImpl movieService = (MovieServiceImpl)
	// context.getBean("movieService");
	// MovieBean mediaBean = new MovieBean();
	// //mediaBean.setName("TestService");
	// movieService.getMedia(mediaId);
	// mediaBean = movieService.getCurrentContextualBean();
	//
	// return mediaBean;
	// }
}
