package de.mediapool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.Application;
import com.vaadin.ui.Window;

import de.mediapool.core.service.MediaWebService;
import de.mediapool.web.ui.MediaMainView;

@SuppressWarnings("serial")
@Configurable
public class MediapoolApplication extends Application {

	@Autowired
	private MediaWebService mediaWebService;

	private MediaMainView mainWindow;

	@Override
	public void init() {
		// getMediaService().createTestData();

		Window window = new Window("mediapool");
		setMainWindow(window);

		setTheme("media");

		mainWindow = new MediaMainView(getMediaWebService());
		window.setContent(mainWindow);

	}

	public MediaWebService getMediaWebService() {
		return mediaWebService;
	}

	public void setMediaWebService(MediaWebService mediaWebService) {
		this.mediaWebService = mediaWebService;
	}

	// @Override
	// public void loggedin(LoggedinEvent event) {
	// // setUser(event.getUser());
	// mainWindow.loggedin(event);
	// }

	// @Override
	// public void loggedin(LoggedinEvent event) {
	// // TODO Auto-generated method stub
	//
	// }

}
