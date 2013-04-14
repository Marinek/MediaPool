package de.mediapool.core.persistence.dao.entities;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityDAO;
import de.mediapool.core.persistence.vo.entities.EntityVO;

@Service
public class EntityDAOImpl extends PSAbstractDAOImpl<EntityVO> implements IEntityDAO {

	public Class<EntityVO> getValueObjectClass() {
		return EntityVO.class;
	}

	public EntityVO getByPrimaryKey(String pPrimaryKey) throws PSException {
		PSCriteria criteria = this.createCriteria();

		criteria.add(Restrictions.eq("id", pPrimaryKey));

		List<EntityVO> results = this.findByCriteria(criteria);

		if (results.size() == 1) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public List<EntityVO> getAllMedia() throws PSException {
		PSCriteria lCriteria = this.createCriteria();

		lCriteria.add(Restrictions.eq("entityType", "movie"));

		return this.findByCriteria(lCriteria);
	}

}
