package de.mediapool.util;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.engine.EntityKey;

public class SessionTracer {
	@SuppressWarnings("unchecked")
	public static void trace(String where, Session session) {
		System.out.println("SessionTracer.trace(" + where + ")");
		System.out.println("\tAddress of Session    : " + System.identityHashCode(session));
		System.out.println("\tTransaction           : " + session.getTransaction());
		System.out.println("\tEntityKeys in Session :");
		for (EntityKey ek : (Set<EntityKey>) session.getStatistics().getEntityKeys())
			System.out.println("\t\t" + ek.getEntityName() + " " + ek.getIdentifier());
		System.out.println();
	}
}
