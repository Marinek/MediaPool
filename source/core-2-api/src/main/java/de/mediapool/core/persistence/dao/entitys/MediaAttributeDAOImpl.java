package de.mediapool.core.persistence.dao.entitys;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.dao.interfaces.media.IMediaAttributesDAO;
import de.mediapool.core.persistence.vo.media.EntityAttributeVO;

public class MediaAttributeDAOImpl extends PSAbstractDAOImpl<EntityAttributeVO> implements IMediaAttributesDAO {

	public Class<EntityAttributeVO> getValueObjectClass() {
		return EntityAttributeVO.class;
	}

	public List<EntityAttributeVO> getAttributesFor(Integer pMediaId) throws PSException {
		PSCriteria criteria = this.createCriteria();
		
		criteria.add(Restrictions.eq("mediaId", pMediaId));
		
		return this.findByCriteria(criteria);
	}

}
