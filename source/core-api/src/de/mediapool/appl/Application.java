package de.mediapool.appl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.business.FilmService;
import de.mediapool.persistence.vo.MovieVO;

public class Application {
	
	
	public static void main(String[] args) {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
		        new String[] {"spring.xml"});
		FilmService service = (FilmService) beanFactory.getBean("filmService");


		MovieVO film = test(service);
		System.out.println(film.getId());
	}
	
	@Autowired
	private static MovieVO test(FilmService service){
		MovieVO lVO = new MovieVO();
		
		lVO.setName("Piraten ;)");
		lVO.setLengthMinutes(1);
		
		service.createFilm(lVO);
		
		return lVO;
	}

}
