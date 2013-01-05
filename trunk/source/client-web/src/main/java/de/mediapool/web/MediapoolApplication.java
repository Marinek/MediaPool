package de.mediapool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.Application;
import com.vaadin.ui.Window;

import de.mediapool.web.service.MediaUiService;
import de.mediapool.web.ui.MediaMainView;

@SuppressWarnings("serial")
@Configurable(preConstruction = true)
public class MediapoolApplication extends Application {

	@Autowired
	private MediaUiService mediaUiService;

	private MediaMainView mainWindow;

	@Override
	public void init() {
		// getMediaService().createTestData();

		Window window = new Window("mediapool");
		setMainWindow(window);

		setTheme("media");

		mainWindow = new MediaMainView(getMediaUiService());
		window.setContent(mainWindow);

	}

	public MediaUiService getMediaUiService() {
		if (mediaUiService == null) {
			SpringContextHelper helper = new SpringContextHelper(this);
			mediaUiService = (MediaUiService) helper.getBean("mediaUiService");
		}

		return mediaUiService;
	}

	public void setMediaUiService(MediaUiService mediaUiService) {
		this.mediaUiService = mediaUiService;
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
