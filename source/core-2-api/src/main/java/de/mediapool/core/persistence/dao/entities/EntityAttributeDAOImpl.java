package de.mediapool.core.persistence.dao.entities;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityAttributesDAO;
import de.mediapool.core.persistence.vo.entities.EntityAttributeVO;

@Service
public class EntityAttributeDAOImpl extends PSAbstractDAOImpl<EntityAttributeVO> implements IEntityAttributesDAO {

	public Class<EntityAttributeVO> getValueObjectClass() {
		return EntityAttributeVO.class;
	}

	public List<EntityAttributeVO> getAttributesFor(String pMediaId) throws PSException {
		PSCriteria criteria = this.createCriteria();
		
		criteria.add(Restrictions.eq("mediaid", pMediaId));
		
		return this.findByCriteria(criteria);
	}

}
