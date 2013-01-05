package de.mediapool.web.client;

import com.vaadin.ui.Window;

public class WebWindow extends Window {

	private static final long serialVersionUID = 1L;

	public WebWindow() {

		// entity manager
		WebEntityManagerView entityManagerView = new WebEntityManagerView();
		setContent(entityManagerView);

		// select window theme
		setTheme("media");
	}
}
