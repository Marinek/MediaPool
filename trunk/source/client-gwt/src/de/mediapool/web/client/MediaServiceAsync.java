package de.mediapool.web.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.mediapool.web.client.dto.Movie;

/**
 * The async counterpart of <code>MediaServiceAsync</code>.
 */
public interface MediaServiceAsync {
	void getAllMovies(AsyncCallback<List<Movie>> callback);
	
}

