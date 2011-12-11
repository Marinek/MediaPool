package de.ompd.medien.server.service;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.ompd.medien.client.GreetingService;
import de.ompd.medien.server.dto.Film;
import de.ompd.medien.server.util.HibernateUtil;
import de.ompd.medien.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		String name = "" + getMockObject().getName();
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent + "id: " + name;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	public Film getMockObject()
	{
 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

 
        //Film object0 = new Film();
        //Film object1 = new Film();
        //session.save(object0);
        //session.save(object1);
        List filme = session.createQuery("from Film").list();
        
        Film object0 = (Film) filme.get(0);
        Film object1 = (Film) filme.get(1);
 
        transaction.commit();
 
//        System.out.println("Object 0");
//        System.out.println("Generated ID is: " + object0.getId());
//        System.out.println("Generated Version is: " + object0.getVersion());
// 
//        System.out.println("Object 1");
//        System.out.println("Generated ID is: " + object1.getId());
//        System.out.println("Generated Version is: " + object1.getVersion());
        return object0;
	}
}
