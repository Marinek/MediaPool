package de.mediapool.core.persistence.dao.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysema.query.sql.MySQLTemplates;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.builder.PSRelationshipCriteriaBuilder;
import de.mediapool.core.persistence.core.sqlbuilder.HibernateSQLQuery;
import de.mediapool.core.persistence.dao.interfaces.search.IProductMediaSearchDAO;
import de.mediapool.core.persistence.vo.entities.EntityVO;
import de.mediapool.core.persistence.vo.entities.QEntityVO;
import de.mediapool.core.persistence.vo.relationship.QRelationshipVO;

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
			
			query.from(new QRelationshipVO("r"));
			
			
			QEntityVO lParent = new QEntityVO("p");
			QEntityVO lChild = new QEntityVO("c");
			
			query.leftJoin(lParent).on(lParent.id.eq(new QRelationshipVO("r").childId));
			query.leftJoin(lChild).on(lChild.id.eq(new QRelationshipVO("r").parentId));
			
			List<Object[]> list = query.list(new QRelationshipVO("r.*"), new QEntityVO("p.*"), new QEntityVO("c.*"));
			
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
