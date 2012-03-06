package de.mediapool.core.business.media;

import de.mediapool.core.beans.media.AbstractMediaBean;
import de.mediapool.core.beans.media.AttributedMediaBean;
import de.mediapool.core.beans.media.MediaType;
import de.mediapool.core.exceptions.MPExeption;

public class BOAttributedMedia extends BOAbstractMedia {

	protected BOAttributedMedia() throws MPExeption {
		super();
	}
	
	protected BOAttributedMedia(int mediaID) throws MPExeption {
		super(mediaID);
	}
	
	protected void init(int mediaID) throws MPExeption {
	
		super.init(mediaID);
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
		
		return super.getCurrentMediaBean();
	}

	protected void protectedSave() throws MPExeption {
	
	}
	
	protected void protectedDelete() throws MPExeption {
	
	}
	
	protected MediaType getMediaType() throws MPExeption {
		return MediaType.ATTRIBUTEDMEDIA;
	}


}
