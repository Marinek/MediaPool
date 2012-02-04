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
package de.mediapool.web.ui.login;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import de.mediapool.core.domain.MUser;

public class LoginForm extends Form implements FormFieldFactory, ClickListener {

	private static final long serialVersionUID = 1L;

	private Button loginButton;
	private HorizontalLayout buttonBar = new HorizontalLayout();

	private MUser user = new MUser();

	public LoginForm() {

		getFooter().addComponent(buttonBar);
		loginButton = new Button("Login", (ClickListener) this);
		buttonBar.addComponent(loginButton);
		setFormFieldFactory(this);
		BeanItem<MUser> userItem = new BeanItem<MUser>(user);
		setItemDataSource(userItem);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (!isValid())
			return;

		Button button = event.getButton();
		if (button == loginButton) {
			try {
				user.authenticate();
				fireLoggedin(user);
			} catch (MUser.BadCredentialsException e) {
				setComponentError(new UserError("LoginForm.BadCredentialsException"));
			}
		}
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

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		String pid = (String) propertyId;
		if (pid.equals("password")) {
			PasswordField passwordField = new PasswordField("password");
			passwordField.setNullRepresentation("");
			return passwordField;
		} else if (pid.equals("email")) {
			TextField tf = new TextField("email");
			tf.setNullRepresentation("");
			return tf;
		}
		return null;
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
}
