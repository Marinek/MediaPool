package de.mediapool.webservice.server;

import javax.xml.ws.Endpoint;
import de.mediapool.webservice.service.MediaWebservice;

public class MediapoolServer {

	public static void main (String args[]) {

		MediaWebservice server = new MediaWebservice();

		Endpoint endpoint =

				Endpoint.publish("http://localhost:8080/MediaWebservice", server);

	}
}
