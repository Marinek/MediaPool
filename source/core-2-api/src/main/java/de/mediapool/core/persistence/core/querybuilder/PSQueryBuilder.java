package de.mediapool.core.persistence.core.querybuilder;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PEntity;

import de.mediapool.core.persistence.core.HibernateSQLQuery;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

public class PSQueryBuilder {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected HibernateSQLQuery currentquery;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public PSQueryBuilder(Session session) throws PSException {
		super();

		currentquery = new HibernateSQLQuery(session, new MySQLTemplates());
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public HibernateSQLQuery getSQLQuery() throws PSException {
		return this.currentquery;
	}

	public final PEntity<?>[] getMappingAsArray() throws PSException {
		List<PEntity<? extends IPSValueObject>> mapping = this.getMapping();

		return mapping.toArray(new PEntity<?>[mapping.size()]);
	}

	public List<PEntity<? extends IPSValueObject>> getMapping() throws PSException {
		return new ArrayList<PEntity<? extends IPSValueObject>>();
	}

	public Query getHibernateQuery() throws PSException {
		return this.getSQLQuery().createQuery(this.getMappingAsArray()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected String getSelection(Path<?> pathName) throws PSException {
		if (pathName == null) {
			throw new IllegalArgumentException("pathName must not be null!");
		}

		return pathName.toString() + " as " + pathName.getMetadata().getRoot() + "_" + pathName.getMetadata().getExpression();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
