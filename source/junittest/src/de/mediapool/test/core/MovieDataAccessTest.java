package de.mediapool.test.core;

import org.junit.Assert;
import org.junit.Test;

import de.mediapool.core.beans.media.MediaType;
import de.mediapool.core.beans.media.MovieBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;

public class MovieDataAccessTest extends DatabaseAccessTest {

	@Test
	public void createFilm() {
		IMediaService lService = beanFactory.getBean(IMediaService.class);

		Assert.assertTrue(lService != null);
		
		MovieBean lVO = new MovieBean();
		
		lVO.setName("Piraten ;)");
		lVO.setLength(132);
		lVO.setMediaType(MediaType.MOVIE);
		
		try {
			lVO = (MovieBean) lService.createMedia(lVO);
			Assert.assertTrue(lVO.getId() !=  0);
			lService.deleteMedia(lVO);
			
			
		MovieBean media = (MovieBean)lService.getMedia(lVO.getId(), lVO.getMediaType());
			
		Assert.assertTrue(media == null);
			
		} catch (MPExeption e) {
			e.printStackTrace();
			Assert.assertTrue("Exception beim Serviceaufruf", 1 == 2);
		}
		
	}
	
	
}
