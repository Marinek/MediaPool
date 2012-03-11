package de.mediapool.core.business.media;

import java.util.List;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.media.AttributedMediaBean;
import de.mediapool.core.beans.media.MediaAttributeBean;
import de.mediapool.core.business.media.attributes.AttributeManager;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.DBException;
import de.mediapool.core.persistence.dao.interfaces.IMediaAttributesDAO;
import de.mediapool.core.persistence.vo.media.MediaAttributeVO;

public class BOAttributedMedia extends BOAbstractMedia {

	private List<MediaAttributeVO> currentAttributes;

	protected BOAttributedMedia() throws MPExeption {
		super();
	}
	
	protected BOAttributedMedia(int mediaID) throws MPExeption {
		super(mediaID);
	}
	
	protected void init(int mediaID) throws MPExeption {
		super.init(mediaID);
		
		try {
			currentAttributes = MediaAttributeVO.getDAO().getAttributesFor(this.currentMediaVO.getId());
		} catch (DBException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Fehler beim Lesen der Attribute", e);
		}
	
	}

	public static BOAttributedMedia getInstance() throws MPExeption {
		return new BOAttributedMedia();
	}
	
	public static BOAttributedMedia getInstance(Integer mediaID) throws MPExeption {
		return new BOAttributedMedia(mediaID);
	}
	
	public AbstractMediaBean getCurrentMediaBean() throws MPExeption {
		if(this.currentMediaBean == null) {
			this.currentMediaBean = new AttributedMediaBean();
		}
		
		if(this.currentAttributes != null) {
			AttributedMediaBean lBean = (AttributedMediaBean) this.currentMediaBean;
			for(MediaAttributeVO lVO : this.currentAttributes) {
				MediaAttributeBean lAttributeBean = new MediaAttributeBean();

				AttributeManager.getInstance().getAttribute(lVO.getAttributeName(), this.currentMediaBean.getMediaType());
				
				lAttributeBean.setAttributeValue(lVO.getAttributeValue());
				
				lBean.addAttribute(lAttributeBean);
			}
		}
		
		return super.getCurrentMediaBean();
	}

	protected void protectedSave() throws MPExeption {
		AttributedMediaBean lBean = (AttributedMediaBean) getCurrentMediaBean();
		
		try {
			
			for(MediaAttributeBean lAttribute : lBean.getAttributes()) {
				MediaAttributeVO lVO = new MediaAttributeVO();

				lVO.setAttributeName("testName");
				lVO.setAttributeValue(lAttribute.getAttributeValue());
				lVO.setMediaID(22);
				
				MediaAttributeVO.getDAO().update(lVO);
			}
		} catch (DBException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_UPDATE, "Attribute konnten nicht hinzugefügt werden.", e);
		}
		
	}
	
	protected void protectedDelete() throws MPExeption {
	
	}

}
