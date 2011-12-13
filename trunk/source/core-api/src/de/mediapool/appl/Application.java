package de.mediapool.appl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.beans.Film;
import de.mediapool.business.FilmService;

public class Application {
	
	
	public static void main(String[] args) {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
		        new String[] {"spring.xml"});
		FilmService service = (FilmService) beanFactory.getBean("filmService");


		Film film = test(service);
		System.out.println(film.getName());
	}
	
	@Autowired
	private static Film test(FilmService service){
		
		return service.getAll().get(0);
		
	}

}
