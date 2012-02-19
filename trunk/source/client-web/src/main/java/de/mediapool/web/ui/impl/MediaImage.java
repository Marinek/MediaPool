package de.mediapool.web.ui.impl;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

@SuppressWarnings("serial")
public class MediaImage extends Embedded implements ClickListener {

	private Window subwindow;
	private Embedded bigImage;

	private final static String THUMB_URL = "http://localhost:81/cover/thumbs/";
	private final static String FULL_URL = "http://localhost:81/cover/";

	public MediaImage() {
		addListener((com.vaadin.event.MouseEvents.ClickListener) this);
		setHeight("200px");

		bigImage = new Embedded();
		bigImage.setHeight("600px");

		// Create the window
		subwindow = new Window("Cover");
		subwindow.addListener(new Window.CloseListener() {
			// inline close-listener
			public void windowClose(CloseEvent e) {
			}
		});

		// Configure the windws layout; by default a VerticalLayout
		VerticalLayout layout = (VerticalLayout) subwindow.getContent();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeUndefined();
		subwindow.addComponent(bigImage);
		subwindow.center();
		subwindow.setResizable(false);

	}

	@Override
	public void click(ClickEvent event) {
		getWindow().addWindow(subwindow);

	}

	public void setTitle(String title) {
		subwindow.setCaption(title);
	}

	public void setFilename(String fileurl, String title, boolean intern) {
		String thumbprefix = "";
		String fullprefix = "";
		this.requestRepaint();
		setTitle(title);
		if (intern) {
			thumbprefix = THUMB_URL;
			fullprefix = FULL_URL;
		}
		this.setSource(new ExternalResource(thumbprefix + fileurl));
		bigImage.setSource(new ExternalResource(fullprefix + fileurl));
	}
}
