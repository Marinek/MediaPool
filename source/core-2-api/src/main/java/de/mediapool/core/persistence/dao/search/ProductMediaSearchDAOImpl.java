package de.mediapool.core.persistence.dao.search;

import java.util.List;

import javax.security.sasl.SaslException;

import org.springframework.stereotype.Service;

import com.mysema.query.hql.hibernate.HibernateQuery;
import com.mysema.query.hql.hibernate.sql.HibernateSQLQuery;
import com.mysema.query.hql.jpa.sql.JPASQLQuery;
import com.mysema.query.sql.MySQLTemplates;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.builder.PSRelationshipCriteriaBuilder;
import de.mediapool.core.persistence.dao.interfaces.search.IProductMediaSearchDAO;
import de.mediapool.core.persistence.vo.SEntities;
import de.mediapool.core.persistence.vo.SRelationships;
import de.mediapool.core.persistence.vo.entities.EntityVO;
import de.mediapool.core.persistence.vo.entities.QEntityVO;
import de.mediapool.core.persistence.vo.relationship.QRelationshipVO;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

@Service
public class ProductMediaSearchDAOImpl extends PSAbstractDAOImpl<EntityVO> implements IProductMediaSearchDAO {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	public List<EntityVO> search () throws PSException {
			PSRelationshipCriteriaBuilder lCriteriaBuilder = new PSRelationshipCriteriaBuilder(EntityVO.class, EntityVO.class);
			
			HibernateSQLQuery  query = new HibernateSQLQuery  (this.getSession(), new MySQLTemplates());
			
			QRelationshipVO lRelationshipVO = new QRelationshipVO("relationships");
			QEntityVO lEntity = new QEntityVO("p");

			query.from(SRelationships.relationships);
			
			SEntities lParent = new SEntities("p");
			SEntities lChild = new SEntities("c");
			
			query.leftJoin(lParent).on(lParent.id.eq(SRelationships.relationships.childid));
			query.leftJoin(lChild).on(lChild.id.eq(SRelationships.relationships.parentid));
			
			List<Object[]> list = query.list(lRelationshipVO, lEntity, new QEntityVO("c"));
			
			return null;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected Class<EntityVO> getValueObjectClass() throws PSException {
		return EntityVO.class;
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
