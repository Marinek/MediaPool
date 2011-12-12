package de.mediapool.appl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import de.mediapool.beans.Film;
import de.mediapool.business.FilmService;

public class Application {
	
	private static boolean callInnerTransaction = false;
	
	public static void main(String[] args) {

		final BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
		final FilmService service = (FilmService) beanFactory.getBean("filmService");


		Film film = test(service);
		System.out.println(film.getName());
	}
	
	@Autowired
	private static Film test(FilmService service){
		
		return service.getAll().get(0);
		
	}

}
