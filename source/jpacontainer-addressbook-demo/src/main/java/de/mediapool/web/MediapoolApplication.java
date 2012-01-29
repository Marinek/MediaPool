package de.mediapool.web;

import com.vaadin.Application;
import com.vaadin.ui.Window;

import de.mediapool.web.ui.login.LoginForm.LoggedinEvent;
import de.mediapool.web.ui.login.LoginForm.LoggedinListener;

@SuppressWarnings("serial")
public class MediapoolApplication extends Application implements LoggedinListener {

	public static final String PERSISTENCE_UNIT = "mediamanager";

	private MediaMainView mainWindow;

	static {
		// MediaService.create();
	}

	@Override
	public void init() {
		Window window = new Window();
		setMainWindow(window);
		setTheme("media");
		mainWindow = new MediaMainView();
		window.setContent(mainWindow);

	}

	@Override
	public void loggedin(LoggedinEvent event) {
		setUser(event.getUser());
		mainWindow.loggedin(event);
	}

}
