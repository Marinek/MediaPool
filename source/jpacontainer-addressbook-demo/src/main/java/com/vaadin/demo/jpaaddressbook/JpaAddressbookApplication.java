package com.vaadin.demo.jpaaddressbook;

import com.vaadin.Application;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class JpaAddressbookApplication extends Application {

	public static final String PERSISTENCE_UNIT = "mediamanager";

	static {
		// MediaService.create();
	}

	@Override
	public void init() {
		Window window = new Window();
		setMainWindow(window);
		setTheme("media");
		window.setContent(new MediaMainView());
	}

}
