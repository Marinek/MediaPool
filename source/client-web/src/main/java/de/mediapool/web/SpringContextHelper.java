package de.mediapool.web;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public class SpringContextHelper {

	private ApplicationContext context;

	public SpringContextHelper(Application application) {
		ServletContext servletContext = ((WebApplicationContext) application.getContext()).getHttpSession().getServletContext();
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	public Object getBean(final String beanRef) {
		return context.getBean(beanRef);
	}
}