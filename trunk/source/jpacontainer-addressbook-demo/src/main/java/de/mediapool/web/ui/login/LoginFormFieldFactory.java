package de.mediapool.web.ui.login;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginFormFieldFactory implements FormFieldFactory {

	private static final long serialVersionUID = 1L;

	public LoginFormFieldFactory() {
		super();
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

}
