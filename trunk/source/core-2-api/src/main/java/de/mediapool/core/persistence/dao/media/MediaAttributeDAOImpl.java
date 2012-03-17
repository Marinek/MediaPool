package de.mediapool.core.persistence.dao.media;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.dao.interfaces.IMediaAttributesDAO;
import de.mediapool.core.persistence.vo.media.MediaAttributeVO;

public class MediaAttributeDAOImpl extends PSAbstractDAOImpl<MediaAttributeVO> implements IMediaAttributesDAO {

	public Class<MediaAttributeVO> getValueObjectClass() {
		return MediaAttributeVO.class;
	}

	public List<MediaAttributeVO> getAttributesFor(Integer pMediaId) throws PSException {
		PSCriteria criteria = this.createCriteria();
		
		criteria.add(Restrictions.eq("mediaId", pMediaId));
		
		return this.findByCriteria(criteria);
	}

}
