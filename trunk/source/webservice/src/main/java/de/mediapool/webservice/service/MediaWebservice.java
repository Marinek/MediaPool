package de.mediapool.webservice.service;

import java.util.UUID;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.media.MediaServiceImpl;

@WebService
@SOAPBinding(style = Style.RPC)
public class MediaWebservice {

	private static final Logger logger = LoggerFactory.getLogger(MediaWebservice.class);
	private MediaServiceImpl mediaService;

	public UserBean getAuthorizedUserBean() {
		UserBean lUser = null;
		// TODO get UserBean
		return lUser;
	}

	/**
	 * Function gets all Media for User
	 * 
	 * @param pUserBean
	 * @return
	 */
	public ProductMediaResultList getAllMedia(UserBean pUserBean) {
		ProductMediaResultList lReturn = null;
		try {
			lReturn = this.mediaService.getAllMedia(pUserBean);
		} catch (MPExeption e) {
			logger.error(e.getLocalizedMessage());
		}
		return lReturn;
	}

	/**
	 * Fuction to save a media for User
	 * 
	 * @param abstractMediaBean
	 * @param pUserBean
	 * @return
	 */
	public MediaBean saveMedia(MediaBean abstractMediaBean, UserBean pUserBean) {
		MediaBean lMedia = null;
		try {
			lMedia = this.mediaService.saveMedia(abstractMediaBean, pUserBean);
		} catch (MPExeption e) {
			// TODO return Fehler
		}
		return lMedia;
	}

	/**
	 * Function to delete a media for User
	 * 
	 * @param abstractMediaBean
	 * @param pUserBean
	 */
	public void deleteMedia(MediaBean abstractMediaBean, UserBean pUserBean) {
		try {
			this.mediaService.deleteMedia(abstractMediaBean, pUserBean);
		} catch (MPExeption e) {
			// TODO return Fehler
		}
	}

	/**
	 * Function to get a media specified by uuid for User
	 * 
	 * @param id
	 * @param pUserBean
	 * @return
	 * @throws MPExeption
	 */
	public MediaBean getMedia(UUID id, UserBean pUserBean) throws MPExeption {
		MediaBean lMedia = null;
		try {
			lMedia = this.mediaService.getMedia(id, pUserBean);
		} catch (MPExeption e) {
			// TODO return Fehler
		}
		return lMedia;
	}

	/**
	 * Funktion to create an attribute for media
	 * 
	 * @param pMediaType
	 * @param pAttributeName
	 * @param pValue
	 * @return
	 */
	public EntityAttributeValueBean createAttribute(String pMediaType, String pAttributeName, String pValue) {
		EntityAttributeValueBean lEntity = null;
		try {
			lEntity = this.mediaService.createAttribute(pMediaType, pAttributeName, pValue);
		} catch (MPExeption e) {
			// TODO return Fehler
		}
		return lEntity;
	}

	/**
	 * Function to create a new media for given type
	 * 
	 * @param pMediaType
	 * @return
	 */
	public MediaBean createNewMedia(String pMediaType) {
		MediaBean lMedia = null;
		try {
			lMedia = this.mediaService.createNewMedia(pMediaType);
		} catch (MPExeption e) {
			// TODO return Fehler
		}
		return lMedia;
	}

	/**
	 * Function to set a product for media
	 * 
	 * @param pReferent
	 * @param pChild
	 */
	public void setProductForMedia(ProductBean pReferent, MediaBean pChild) {
		try {
			this.mediaService.setProductForMedia(pReferent, pChild);
		} catch (MPExeption e) {
			// TODO return Fehler
		}

	}

	// ********** Getter und Setter **********//

	/**
	 * Function to set the mediaservice
	 * 
	 * @return
	 */
	public MediaServiceImpl getMediaService() {
		return mediaService;
	}

	/**
	 * Function to get the mediaservice
	 * 
	 * @param mediaService
	 */
	public void setMediaService(MediaServiceImpl mediaService) {
		this.mediaService = mediaService;
	}
	// ****************************************//
}
