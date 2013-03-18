package de.mediapool.core.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mediapool.core.services.interfaces.IAuthService;
import de.mediapool.core.services.interfaces.IInstallationService;
import de.mediapool.core.services.interfaces.IMediaMetaDataService;
import de.mediapool.core.services.interfaces.IMediaService;
import de.mediapool.core.services.interfaces.IProductService;
import de.mediapool.core.services.interfaces.ISearchService;

public class MPLocalService {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static MPLocalService myInstance = null;

	ClassPathXmlApplicationContext beanFactory = null;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private MPLocalService() {
	}

	public static MPLocalService getInstance() {
		if (myInstance == null) {
			myInstance = new MPLocalService();

			myInstance.init();
		}
		return myInstance;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public IMediaMetaDataService getMediaMetaDataService() {
		return (IMediaMetaDataService) beanFactory.getBean("mediaMetaDataService");
	}

	public IMediaService getMediaService() {
		return (IMediaService) beanFactory.getBean("mediaService");
	}

	public IProductService getProductService() {
		return (IProductService) beanFactory.getBean("productService");
	}

	public IAuthService getAuthService() {
		return (IAuthService) beanFactory.getBean("authService");
	}

	public IInstallationService getInstallationService() {
		return (IInstallationService) beanFactory.getBean("installationService");
	}

	public ISearchService getSearchService() {
		return (ISearchService) beanFactory.getBean("searchService");
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private void init() {
		beanFactory = new ClassPathXmlApplicationContext(new String[] { "applicationCoreContext.xml" });
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
