package de.mediapool.web.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.mediapool.web.client.dto.Movie;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("media")
public interface MediaService extends RemoteService {
	List<Movie> searchMedia(Movie movie) throws IllegalArgumentException;

	List<Movie> createMedia(Movie movie) throws IllegalArgumentException;

	List<Movie> deleteMedia(Movie movie) throws IllegalArgumentException;

	List<Movie> updateMedia(Movie movie) throws IllegalArgumentException;

}
