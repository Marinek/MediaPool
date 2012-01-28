package de.mediapool.web;

import com.vaadin.Application;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MediapoolApplication extends Application {

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