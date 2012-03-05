package de.mediapool.core.business.media;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import de.mediapool.core.beans.media.MediaType;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;

public class BOMediaTypeManager extends BusinessObject {

	private static BOMediaTypeManager instance = null;
	
	private Map<MediaType, Class<? extends BOAbstractMedia>> mediaTypeMap = new HashMap<MediaType, Class<? extends BOAbstractMedia>>();
	
	private BOMediaTypeManager () {
		this.init();
	}
	
	private void init() {
		this.mediaTypeMap.put(MediaType.ATTRIBUTEDMEDIA, BOAttributedMedia.class);
	}

	public static final BOMediaTypeManager getInstance() {
		if(instance == null) {
			instance = new BOMediaTypeManager();
		}
		
		return instance;
	}
	
	public BOAbstractMedia getBOInstance (Integer mediaID, MediaType mediaType) throws MPExeption {
		BOAbstractMedia boInstance = null;
		
		if(this.mediaTypeMap.containsKey(mediaType)) {
			Class<? extends BOAbstractMedia> boClass = this.mediaTypeMap.get(mediaType);
			
			try {
				Method getInstance;
				if(mediaID != null) {
					getInstance = boClass.getMethod("getInstance", Integer.class);
					boInstance = (BOAbstractMedia) getInstance.invoke(null, new Object[] {mediaID});
				} else {
					getInstance = boClass.getMethod("getInstance", new Class<?>[] {});
					boInstance = (BOAbstractMedia) getInstance.invoke(null, new Object[] {});
				}
				
			} catch (IllegalAccessException e) {
				throw new MPTechnicalExeption(ExeptionErrorCode.MANAGER_CREATE, "(IllegalAccessException) Could not instance BO for " + mediaType, e);
			} catch (SecurityException e) {
				throw new MPTechnicalExeption(ExeptionErrorCode.MANAGER_CREATE, "(SecurityException) Could not instance BO for " + mediaType, e);
			} catch (NoSuchMethodException e) {
				throw new MPTechnicalExeption(ExeptionErrorCode.MANAGER_CREATE, "(NoSuchMethodException) Could not instance BO for " + mediaType, e);
			} catch (IllegalArgumentException e) {
				throw new MPTechnicalExeption(ExeptionErrorCode.MANAGER_CREATE, "(IllegalArgumentException) Could not instance BO for " + mediaType, e);
			} catch (InvocationTargetException e) {
				throw new MPTechnicalExeption(ExeptionErrorCode.MANAGER_CREATE, "(InvocationTargetException) Could not instance BO for " + mediaType, e);
			}
		}
		
		return boInstance;
	}
	
	public BOAbstractMedia getBOInstance (Integer mediaId) throws MPExeption {
		return this.getBOInstance(mediaId, MediaType.ATTRIBUTEDMEDIA);
	}
	
	public BOAbstractMedia getBOInstance (MediaType mediaType) throws MPExeption {
		return this.getBOInstance(null, mediaType);
	}
}
