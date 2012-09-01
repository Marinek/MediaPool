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
	
	private final QRelationshipVO relationship  =  new QRelationshipVO("r");

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public PSRelationshipSQLBuilder(Session session) throws PSException {
		super(session);
		
	}
	
	public List<PEntity<? extends IPSValueObject>> getMapping() throws PSException {
		List<PEntity<? extends IPSValueObject>> mapping = super.getMapping();
		
		mapping.add(new QRelationshipVO(this.getSelection(relationship.changedAt)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.childEntity)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.childId)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.childType)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.createdAt)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.createdBy)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.id)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.parentId)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.parentType)));
		mapping.add(new QRelationshipVO(this.getSelection(relationship.relationType)));
		
		return mapping;
	}
	
	public HibernateSQLQuery getSQLQuery() throws PSException {
		 HibernateSQLQuery sqlQuery = super.getSQLQuery();

		 sqlQuery.from(relationship);
		 
		 return sqlQuery;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
