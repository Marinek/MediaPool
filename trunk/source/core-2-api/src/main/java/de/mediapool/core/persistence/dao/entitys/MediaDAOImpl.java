package de.mediapool.core.persistence.dao.entitys;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.dao.interfaces.media.IMediaDAO;
import de.mediapool.core.persistence.vo.media.EntityVO;


public class MediaDAOImpl extends PSAbstractDAOImpl<EntityVO> implements IMediaDAO {

	public Class<EntityVO> getValueObjectClass() {
		return EntityVO.class;
	}

	public List<EntityVO> getAll() {
		return null;
	}

	public EntityVO getByPrimaryKey(Integer pPrimaryKey) throws PSException {
		PSCriteria criteria = this.createCriteria();
		
		criteria.add(Restrictions.eq("id", pPrimaryKey));
		
		List<EntityVO> results = this.findByCriteria(criteria);
		
		if(results.size() == 1) {
			return results.get(0);
		}
		return null;
	}


}
