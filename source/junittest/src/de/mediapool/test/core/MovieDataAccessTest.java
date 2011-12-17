package de.mediapool.test.core;

import org.junit.Assert;
import org.junit.Test;

import de.mediapool.beans.media.MovieBean;
import de.mediapool.business.interfaces.IMediaService;
import de.mediapool.business.interfaces.IMovieService;
import de.mediapool.exceptions.MPExeption;

public class MovieDataAccessTest extends DatabaseAccessTest {

	@Test
	public void createFilm() {
		IMediaService lService = beanFactory.getBean(IMovieService.class);

		Assert.assertTrue(lService != null);
		
		MovieBean lVO = new MovieBean();
		
		lVO.setName("Piraten ;)");
		lVO.setLength(132);
		
		try {
			lService.createMedia(lVO);
			Assert.assertTrue(lVO.getId() !=  0);
			lService.deleteMedia(lVO);
			
			
			MovieBean media = (MovieBean)lService.getMedia(lVO.getId());
			
			Assert.assertTrue(media == null);
			
		} catch (MPExeption e) {
			Assert.assertTrue("Exception beim Serviceaufruff", 1 == 2);
		}
		
	}
	
	
}
