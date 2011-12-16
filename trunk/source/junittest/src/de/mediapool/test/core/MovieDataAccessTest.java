package de.mediapool.test.core;

import org.junit.Assert;
import org.junit.Test;

import de.mediapool.business.FilmService;
import de.mediapool.persistence.vo.MovieVO;

public class MovieDataAccessTest extends DatabaseAccessTest {

	@Test
	public void createFilm() {
		FilmService lService = beanFactory.getBean(FilmService.class);

		Assert.assertTrue(lService != null);
		
		MovieVO lVO = new MovieVO();
		
		lVO.setName("Piraten ;)");
		lVO.setLengthMinutes(132);
		
		lService.createFilm(lVO);
		
		Assert.assertTrue(lVO.getId() !=  0);
		
		
		lService.deleteFilm(lVO);
		
		MovieVO lFilmJustCreated = lService.getFilm(lVO.getId());
		
		
		Assert.assertTrue(lFilmJustCreated == null);
		
	}
	
	@Test
	public void createAndReadFilm() {
		FilmService lService = beanFactory.getBean(FilmService.class);

		Assert.assertTrue(lService != null);
		
		MovieVO lVO = new MovieVO();
		
		lVO.setName("Piraten ;)");
		lVO.setLengthMinutes(132);
		
		lService.createFilm(lVO);
		
		Assert.assertTrue(lVO.getId() !=  0);
		
		
		MovieVO lFilmJustCreated = lService.getFilm(lVO.getId());
		
		
		Assert.assertTrue(lFilmJustCreated != null);
		
		

		lService.deleteFilm(lFilmJustCreated);
		
		MovieVO lFilmJustDeleted = lService.getFilm(lFilmJustCreated.getId());
		
		Assert.assertTrue(lFilmJustDeleted == null);
	}
}
