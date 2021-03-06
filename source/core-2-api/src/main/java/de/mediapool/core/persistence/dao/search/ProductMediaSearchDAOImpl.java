package de.mediapool.core.persistence.dao.search;

import java.util.List;

import org.springframework.stereotype.Service;

import de.mediapool.core.beans.search.entity.joined.ProductMediaSearchBean;
import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.querybuilder.relationship.PSEntityRelationType;
import de.mediapool.core.persistence.core.querybuilder.relationship.PSEntityRelationshipSQLBuilder;
import de.mediapool.core.persistence.dao.interfaces.search.IProductMediaSearchDAO;
import de.mediapool.core.persistence.vo.joined.relationship.ProductMediaJoinedVO;

@Service
public class ProductMediaSearchDAOImpl extends PSAbstractDAOImpl<ProductMediaJoinedVO> implements IProductMediaSearchDAO {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public List<ProductMediaJoinedVO> search(ProductMediaSearchBean searchCriteria) throws PSException {
		PSEntityRelationshipSQLBuilder lBuilder = new PSEntityRelationshipSQLBuilder(this.getSession());

		lBuilder.addEntityCriteria(PSEntityRelationType.PARENT, searchCriteria.getProductCriteria());
		lBuilder.addEntityCriteria(PSEntityRelationType.CHILD, searchCriteria.geMediaCriteria());

		return this.findByBuilder(lBuilder);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected Class<ProductMediaJoinedVO> getValueObjectClass() throws PSException {
		return ProductMediaJoinedVO.class;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
