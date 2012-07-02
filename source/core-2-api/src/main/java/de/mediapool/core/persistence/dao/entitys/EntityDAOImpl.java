package de.mediapool.core.persistence.dao.entitys;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.Restrictions;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.dao.interfaces.media.IEntityDAO;
import de.mediapool.core.persistence.vo.media.EntityVO;


public class EntityDAOImpl extends PSAbstractDAOImpl<EntityVO> implements IEntityDAO {

	public Class<EntityVO> getValueObjectClass() {
		return EntityVO.class;
	}

	public List<EntityVO> getAll() {
		return null;
	}

	public EntityVO getByPrimaryKey(UUID pPrimaryKey) throws PSException {
		PSCriteria criteria = this.createCriteria();
		
		criteria.add(Restrictions.eq("id", pPrimaryKey));
		
		List<EntityVO> results = this.findByCriteria(criteria);
		
		if(results.size() == 1) {
			return results.get(0);
		}
		return null;
	}


}
