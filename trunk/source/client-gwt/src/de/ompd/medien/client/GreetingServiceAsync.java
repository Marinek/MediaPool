package de.ompd.medien.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.mediapool.beans.media.AbstractMediaBean;
import de.mediapool.exceptions.MPExeption;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void getMedia(int mediaId, AsyncCallback<AbstractMediaBean> callback) throws MPExeption;
}

