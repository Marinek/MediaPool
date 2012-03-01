package de.mediapool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.Application;
import com.vaadin.ui.Window;

import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.MediaMainView;
import de.mediapool.web.ui.login.MediaLoginForm.LoggedinEvent;
import de.mediapool.web.ui.login.MediaLoginForm.LoggedinListener;

@SuppressWarnings("serial")
@Configurable
public class MediapoolApplication extends Application implements LoggedinListener {

	@Autowired
	private MediaService mediaService;

	private MediaMainView mainWindow;

	@Override
	public void init() {
		// getMediaService().createTestData();

		Window window = new Window("mediapool");
		setMainWindow(window);

		setTheme("media");

		mainWindow = new MediaMainView(getMediaService());
		window.setContent(mainWindow);

	}

	@Override
	public void loggedin(LoggedinEvent event) {
		setUser(event.getUser());
		mainWindow.loggedin(event);
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

}
