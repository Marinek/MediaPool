package de.mediapool.core.persistence.core.querybuilder.relationship;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.mysema.query.types.path.PEntity;
import com.mysema.query.types.path.PString;

import de.mediapool.core.beans.search.AbstractCriteriaBean;
import de.mediapool.core.persistence.core.HibernateSQLQuery;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.vo.entities.QEntityAttributeVO;
import de.mediapool.core.persistence.vo.entities.QEntityVO;

public class PSEntityRelationshipSQLBuilder extends PSRelationshipSQLBuilder {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private final QEntityVO parent = new QEntityVO("parent");
	private final QEntityVO child = new QEntityVO("child");

	private List<AbstractCriteriaBean> parentAttributes = new ArrayList<AbstractCriteriaBean>();
	private List<AbstractCriteriaBean> childAttributes = new ArrayList<AbstractCriteriaBean>();

	private int criteriaCount = 0;

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

		sqlQuery.leftJoin(parent).on(parent.id.eq(this.getRelationship().parentId));
		sqlQuery.leftJoin(child).on(child.id.eq(this.getRelationship().childId));

		this.addAttributeRestrictions(parentAttributes, this.getRelationship().parentId, sqlQuery);
		this.addAttributeRestrictions(childAttributes, this.getRelationship().childId, sqlQuery);

		return sqlQuery;
	}

	public void addEntityCriteria(PSEntityRelationType pType, List<AbstractCriteriaBean> criteria) throws PSException {
		switch (pType) {
		case PARENT:
			this.parentAttributes.addAll(criteria);
			break;
		case CHILD:
			this.childAttributes.addAll(criteria);
			break;
		default:
			throw new IllegalArgumentException("Undefined RelationType!");
		}
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected void addAttributeRestrictions(List<AbstractCriteriaBean> pCriteriaList, PString matchingField, HibernateSQLQuery sqlQuery) throws PSException {
		for (AbstractCriteriaBean entityCriteria : pCriteriaList) {
			QEntityAttributeVO qEntityAttributeVO = new QEntityAttributeVO("attr" + String.valueOf(criteriaCount++));

			sqlQuery.leftJoin(qEntityAttributeVO).on(qEntityAttributeVO.entityid.eq(matchingField));

			sqlQuery.where(qEntityAttributeVO.attributeName.eq(entityCriteria.getField()));
			switch (entityCriteria.getOperation()) {
			case BETWEEN:
				sqlQuery.where(qEntityAttributeVO.attributeValue.between(entityCriteria.getValues().get(0), entityCriteria.getValues().get(1)));
				break;
			case EQ:
				sqlQuery.where(qEntityAttributeVO.attributeValue.eq(entityCriteria.getSingleValue()));
				break;
			case GT:
				sqlQuery.where(qEntityAttributeVO.attributeValue.gt(entityCriteria.getSingleValue()));
				break;
			case IN:
				sqlQuery.where(qEntityAttributeVO.attributeValue.in(entityCriteria.getValues()));
				break;
			case LIKE:
				sqlQuery.where(qEntityAttributeVO.attributeValue.like(entityCriteria.getSingleValue()));
				break;
			case LT:
				sqlQuery.where(qEntityAttributeVO.attributeValue.lt(entityCriteria.getSingleValue()));
				break;
			default:
				throw new IllegalArgumentException("Undefined OperationType!");
			}
		}
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
