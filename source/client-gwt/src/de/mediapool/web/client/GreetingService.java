package de.mediapool.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.mediapool.beans.media.AbstractMediaBean;
import de.mediapool.exceptions.MPExeption;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	public AbstractMediaBean getMedia(int mediaId) throws MPExeption;
}
