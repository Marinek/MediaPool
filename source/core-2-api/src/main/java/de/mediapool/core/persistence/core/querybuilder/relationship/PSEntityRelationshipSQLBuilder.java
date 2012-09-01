package de.mediapool.core.persistence.core.querybuilder.relationship;

import java.util.List;

import org.hibernate.Session;

import com.mysema.query.types.path.PEntity;

import de.mediapool.core.persistence.core.HibernateSQLQuery;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.vo.entities.QEntityVO;
import de.mediapool.core.persistence.vo.relationship.QRelationshipVO;

public class PSEntityRelationshipSQLBuilder extends	PSRelationshipSQLBuilder {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	private final QEntityVO parent = new QEntityVO("parent");
	private final QEntityVO child = new QEntityVO("child");

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public PSEntityRelationshipSQLBuilder(Session session) throws PSException {
		super(session);
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	public List<PEntity<? extends IPSValueObject>> getMapping() throws PSException {
		List<PEntity<? extends IPSValueObject>> mapping = super.getMapping();
		
		mapping.add(new QEntityVO(getSelection(parent.changedAt)));
		mapping.add(new QEntityVO(getSelection(parent.changedBy)));
		mapping.add(new QEntityVO(getSelection(parent.createdAt)));
		mapping.add(new QEntityVO(getSelection(parent.entityType)));
		mapping.add(new QEntityVO(getSelection(parent.id)));
		mapping.add(new QEntityVO(getSelection(parent.name)));

		mapping.add(new QEntityVO(getSelection(child.changedAt)));
		mapping.add(new QEntityVO(getSelection(child.changedBy)));
		mapping.add(new QEntityVO(getSelection(child.createdAt)));
		mapping.add(new QEntityVO(getSelection(child.entityType)));
		mapping.add(new QEntityVO(getSelection(child.id)));
		mapping.add(new QEntityVO(getSelection(child.name)));

		return mapping;
		
	}

	public HibernateSQLQuery getSQLQuery() throws PSException {
		 HibernateSQLQuery sqlQuery = super.getSQLQuery();
		 
		 sqlQuery.leftJoin(parent).on(parent.id.eq(new QRelationshipVO("r").parentId));
		 sqlQuery.leftJoin(child).on(child.id.eq(new QRelationshipVO("r").childId));
		 
		 return sqlQuery;
	}

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
