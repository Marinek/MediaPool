package de.mediapool.webservice.server;

import javax.xml.ws.Endpoint;

import de.mediapool.webservice.service.MediaWebservice;

// http://localhost:8080/MediaWebservice?wsdl
// wsimport -keep http://localhost:8080/MediaWebservide?wsdl

public class MediapoolServer {

	public static void main(String args[]) {

		MediaWebservice server = new MediaWebservice();

		Endpoint endpoint =

		Endpoint.publish("http://localhost:7979/MediaWebservice", server);

	}
}
