package de.mediapool.test.core;

import org.junit.Assert;
import org.junit.Test;

import de.mediapool.core.beans.media.MovieBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.interfaces.IMediaService;
import de.mediapool.core.services.interfaces.IMovieService;

public class MovieDataAccessTest extends DatabaseAccessTest {

	@Test
	public void createFilm() {
		IMediaService lService = beanFactory.getBean(IMovieService.class);

		Assert.assertTrue(lService != null);
		
		MovieBean lVO = new MovieBean();
		
		lVO.setName("Piraten ;)");
		lVO.setLength(132);
		
		try {
			lService.setCurrentContextualBean(lVO);
			lService.createMedia();
			Assert.assertTrue(lVO.getId() !=  0);
			lService.deleteMedia();
			
			
//			MovieBean media = (MovieBean)lService.getMedia(lVO.getId());
			
//			Assert.assertTrue(media == null);
			
		} catch (MPExeption e) {
			Assert.assertTrue("Exception beim Serviceaufruff", 1 == 2);
		}
		
	}
	
	
}
