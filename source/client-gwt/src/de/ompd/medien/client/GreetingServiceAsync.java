package de.ompd.medien.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.ompd.medien.server.dto.Film;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
