package com.vaadin.demo.jpaaddressbook;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class JpaAddressbookApplication extends Application {

	public static final String PERSISTENCE_UNIT = "addressbook";

	static {
		// DemoDataGenerator.create();
	}

	@Override
	public void init() {
		Window window = new Window();
		setMainWindow(window);
		setTheme("media");
		window.setContent(new AddressBookMainView());
	}

}
