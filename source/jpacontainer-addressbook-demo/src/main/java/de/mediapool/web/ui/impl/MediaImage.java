package de.mediapool.web.ui.impl;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

@SuppressWarnings("serial")
public class MediaImage extends Embedded implements ClickListener {

	private Window subwindow;
	private Embedded bigImage;

	public MediaImage() {
		addListener((com.vaadin.event.MouseEvents.ClickListener) this);
		setWidth("200px");

		bigImage = new Embedded();
		bigImage.setWidth("1024px");

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

	}

	@Override
	public void click(ClickEvent event) {
		getWindow().addWindow(subwindow);

	}

	public void setTitle(String title) {
		subwindow.setCaption(title);
	}

	public void setFilename(String fileurl, String title) {
		this.requestRepaint();
		setTitle(title);
		this.setSource(new ThemeResource("cover/" + fileurl));
		bigImage.setSource(new ThemeResource("cover/" + fileurl));
	}

}
