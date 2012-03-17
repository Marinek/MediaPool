package de.mediapool.core.persistence.dao.media;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.dao.interfaces.IMediaDAO;
import de.mediapool.core.persistence.vo.media.MediaVO;


public class MediaDAOImpl extends PSAbstractDAOImpl<MediaVO> implements IMediaDAO {

	public Class<MediaVO> getValueObjectClass() {
		return MediaVO.class;
	}

	public List<MediaVO> getAll() {
		return null;
	}

	public MediaVO getByPrimaryKey(Integer pPrimaryKey) throws PSException {
		PSCriteria criteria = this.createCriteria();
		
		criteria.add(Restrictions.eq("id", pPrimaryKey));
		
		List<MediaVO> results = this.findByCriteria(criteria);
		
		if(results.size() == 1) {
			return results.get(0);
		}
		return null;
	}


}
