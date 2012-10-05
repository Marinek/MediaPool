package de.mediapool.web.ui.elements;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

import de.mediapool.core.MediaInterface;

@SuppressWarnings("serial")
public class MediaImage extends Embedded implements ClickListener {
	private final Logger logger = LoggerFactory.getLogger(MediaImage.class);

	private Window subwindow;
	private Embedded bigImage;

	BeanItem<MediaInterface> mediaItem;

	private String thumb_url;
	private String big_url;

	private boolean intialized = false;

	public MediaImage() {
		this(true, "200px");
	}

	private void intialize() {
		if (!intialized) {
			Configuration config;
			try {
				config = new PropertiesConfiguration("mediapool.properties");
				thumb_url = config.getString("thumb_url");
				big_url = config.getString("big_url");
				intialized = true;
			} catch (ConfigurationException e) {
				logger.error(e.getMessage());
			}
		}
	}

	public MediaImage(boolean clickable, String size) {
		if (clickable) {
			addListener((com.vaadin.event.MouseEvents.ClickListener) this);
		}
		setHeight(size);

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

	private void setFilename(String fileurl, String title, boolean intern) {
		intialize();
		String thumbprefix = "";
		String fullprefix = "";
		this.requestRepaint();
		setTitle(title);
		if (intern) {
			thumbprefix = thumb_url;
			fullprefix = big_url;
		}
		this.setSource(new ExternalResource(thumbprefix + fileurl));
		bigImage.setSource(new ExternalResource(fullprefix + fileurl));
	}

	private void changeImage() {
		Boolean localItem = false;
		Property cover = mediaItem.getItemProperty("cover");
		Property local = mediaItem.getItemProperty("local");

		if (local != null) {
			localItem = (Boolean) local.getValue();
		}
		if (cover.getValue() == null) {
			cover = mediaItem.getItemProperty("image");
		}
		Property title = mediaItem.getItemProperty("title");
		setFilename(nullCheck(cover), nullCheck(title), localItem);

	}

	private String nullCheck(Property check) {
		String string = "";
		if (check != null) {
			string = (String) check.getValue();
		}
		return string;
	}

	public BeanItem<MediaInterface> getMediaItem() {
		return mediaItem;
	}

	public void setMediaItem(BeanItem<MediaInterface> mediaItem) {
		this.mediaItem = mediaItem;
		changeImage();
	}
}
