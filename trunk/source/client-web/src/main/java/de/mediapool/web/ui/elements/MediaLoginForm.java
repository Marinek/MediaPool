/*
 *    Copyright 2010 Peter Backx, streamhead.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *    
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package de.mediapool.web.ui.elements;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.ui.Component;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

import de.mediapool.core.domain.MUser;
import de.mediapool.core.service.MediaService;
import de.mediapool.core.service.MediaService.WrongUserException;

@SuppressWarnings("serial")
@Configurable
public class MediaLoginForm extends VerticalLayout implements LoginForm.LoginListener {

	private static final String LOGIN_FAILED = "Der Login ist fehlgeschlagen";

	@Autowired
	private MediaService mediaService;

	public MediaLoginForm() {

		setImmediate(true);
		LoginForm login = new LoginForm();

		setMargin(false, false, false, true);
		setHeight("110px");
		login.setHeight("110px");
		setSpacing(true);
		login.addListener((LoginForm.LoginListener) this);
		addComponent(login);

	}

	// below is all about events & handling them

	private static final Method LOGGEDIN_METHOD;
	static {
		try {
			LOGGEDIN_METHOD = LoggedinListener.class.getDeclaredMethod("loggedin", new Class[] { LoggedinEvent.class });
		} catch (NoSuchMethodException e) {
			// This should never happen
			throw new java.lang.RuntimeException("Internal error finding methods in Button");
		}
	}

	public class LoggedinEvent extends Component.Event {
		private static final long serialVersionUID = 1L;
		private MUser user;

		public LoggedinEvent(Component source, MUser user) {
			super(source);
			this.user = user;
		}

		public MUser getUser() {
			return user;
		}
	}

	public interface LoggedinListener extends Serializable {
		public void loggedin(LoggedinEvent event);
	}

	public void addLoginListener(LoggedinListener listener) {
		addListener(LoggedinEvent.class, listener, LOGGEDIN_METHOD);
	}

	public void removeLoginListener(LoggedinListener listener) {
		removeListener(LoggedinEvent.class, listener, LOGGEDIN_METHOD);
	}

	protected void fireLoggedin(MUser user) {
		fireEvent(new LoggedinEvent(this, user));
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	@Override
	public void onLogin(LoginEvent event) {
		String email = event.getLoginParameter("username");
		String password = event.getLoginParameter("password");
		MUser user;
		try {
			user = getMediaService().loginMUser(email, password);
			fireLoggedin(user);
		} catch (WrongUserException e) {
			getWindow().showNotification(LOGIN_FAILED, Notification.TYPE_ERROR_MESSAGE);

		}

	}
}
