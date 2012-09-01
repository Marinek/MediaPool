package de.mediapool.core.persistence.dao.search;

import java.util.List;

import org.springframework.stereotype.Service;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.querybuilder.relationship.PSEntityRelationshipSQLBuilder;
import de.mediapool.core.persistence.dao.interfaces.search.IProductMediaSearchDAO;
import de.mediapool.core.persistence.vo.entities.EntityVO;
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
	
	public List<ProductMediaJoinedVO> search () throws PSException {
			PSEntityRelationshipSQLBuilder lBuilder = new PSEntityRelationshipSQLBuilder(this.getSession());
			
			
		
			return this.findByBuilder(lBuilder);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected Class<ProductMediaJoinedVO> getValueObjectClass() throws PSException {
		return ProductMediaJoinedVO.class;
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