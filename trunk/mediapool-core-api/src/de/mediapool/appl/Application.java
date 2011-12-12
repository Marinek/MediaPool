package de.mediapool.appl;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import de.mediapool.beans.Film;
import de.mediapool.business.FilmService;

public class Application {
	
	private static boolean callInnerTransaction = false;
	
	public static void main(String[] args) {

		final BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
		final PlatformTransactionManager tm = 
			(PlatformTransactionManager) beanFactory.getBean("transactionManager");
		final FilmService service = (FilmService) beanFactory.getBean("filmService");

		transaction1(tm, service);
		if (! callInnerTransaction)
			transaction2(tm, service);
		Film film = transaction3(tm, service);
		System.out.println(film.getName());
	}
	
	private static void transaction1(PlatformTransactionManager tm, FilmService service) {
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus ts = tm.getTransaction(td);
		try {
//			service.createAccount(4711);
//			service.createAccount(4712);
			if (callInnerTransaction)
				transaction2(tm, service);
			tm.commit(ts);
		}
		catch(RuntimeException e) {
			tm.rollback(ts);
			throw e;
		}
	}
	private static void transaction2(PlatformTransactionManager tm, FilmService service) {
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus ts = tm.getTransaction(td);
		try {
//			service.deposit(4711, 5000);
//			service.deposit(4712, 6000);
//			service.withdraw(4711, 2000);
//			service.transfer(4711, 4712, 500);
			tm.commit(ts);
		}
		catch(RuntimeException e) {
			tm.rollback(ts);
			throw e;
		}
	}
	private static Film transaction3(PlatformTransactionManager tm, FilmService service) {
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus ts = tm.getTransaction(td);
		try {
			List<Film> filme = service.getAll();
			tm.commit(ts);
			return filme.get(0);
		}
		catch(RuntimeException e) {
			tm.rollback(ts);
			throw e;
		}
	}
}
