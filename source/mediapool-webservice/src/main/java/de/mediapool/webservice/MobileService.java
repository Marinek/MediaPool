package de.mediapool.webservice;

public class MobileService {

	public String sayHello(String name) {
		if (name == null) {
			return "Hello";
		}

		return "Hello, " + name + "!";
	}

}
