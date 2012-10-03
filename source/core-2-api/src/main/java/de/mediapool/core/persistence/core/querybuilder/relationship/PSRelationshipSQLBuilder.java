package de.mediapool.core.persistence.core.querybuilder.relationship;

import java.util.List;

import org.hibernate.Session;

import com.mysema.query.types.path.PEntity;

import de.mediapool.core.persistence.core.HibernateSQLQuery;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.core.querybuilder.PSJoinedCriteriaBuilder;
import de.mediapool.core.persistence.vo.relationship.QRelationshipVO;

public class PSRelationshipSQLBuilder extends PSJoinedCriteriaBuilder {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private final QRelationshipVO relationship = new QRelationshipVO("r");

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public PSRelationshipSQLBuilder(Session session) throws PSException {
		super(session);

	}

	public List<PEntity<? extends IPSValueObject>> getMapping() throws PSException {
		List<PEntity<? extends IPSValueObject>> mapping = super.getMapping();

		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().changedAt)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().childEntity)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().childId)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().childType)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().createdAt)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().createdBy)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().id)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().parentId)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().parentType)));
		mapping.add(new QRelationshipVO(this.getSelection(getRelationship().relationType)));

		return mapping;
	}

	public HibernateSQLQuery getSQLQuery() throws PSException {
		HibernateSQLQuery sqlQuery = super.getSQLQuery();

		sqlQuery.from(getRelationship());

		return sqlQuery;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected QRelationshipVO getRelationship() {
		return relationship;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
