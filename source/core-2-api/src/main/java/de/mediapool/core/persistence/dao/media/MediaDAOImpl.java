package de.mediapool.core.persistence.dao.media;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import de.mediapool.core.persistence.core.AbstractDAOImpl;
import de.mediapool.core.persistence.core.DBException;
import de.mediapool.core.persistence.dao.interfaces.IMediaDAO;
import de.mediapool.core.persistence.vo.media.MediaVO;


public class MediaDAOImpl extends AbstractDAOImpl<MediaVO> implements IMediaDAO {

	public Class<MediaVO> getValueObjectClass() {
		return MediaVO.class;
	}

	public List<MediaVO> getAll() {
		return null;
	}

	public MediaVO getByPrimaryKey(Integer pPrimaryKey) throws DBException {
		Criteria criteria = this.createCriteria();
		
		criteria.add(Restrictions.eq("id", pPrimaryKey));
		
		List<MediaVO> results = criteria.list();
		
		if(results.size() == 1) {
			return results.get(0);
		}
		return null;
	}


}
