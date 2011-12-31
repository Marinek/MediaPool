package de.mediapool.web.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.mediapool.web.client.dto.Movie;

/**
 * The async counterpart of <code>MediaServiceAsync</code>.
 */
public interface MediaServiceAsync {
	void searchMedia(Movie movie, AsyncCallback<List<Movie>> callback);

	void updateMedia(Movie movie, AsyncCallback<List<Movie>> callback);

	void createMedia(Movie movie, AsyncCallback<List<Movie>> callback);

	void deleteMedia(Movie movie, AsyncCallback<List<Movie>> callback);

}
