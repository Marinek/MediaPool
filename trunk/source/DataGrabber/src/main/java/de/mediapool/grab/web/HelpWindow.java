package de.mediapool.grab.web;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import de.mediapool.grab.util.JsoupWiki;

@SuppressWarnings("serial")
public class HelpWindow extends Window implements ClickListener {
	private static final String HELP_HTML_SNIPPET = "This is " + "an application built during <strong><a href=\""
			+ "http://dev.vaadin.com/\">Vaadin</a></strong> " + "tutorial. Hopefully it doesn't need any real help.";

	public HelpWindow() {
		setCaption("Film Suche");
		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponent(new Label("Suche"));
		hl.addComponent(new TextField());
		hl.addComponent(new Button("Los", this));

		addComponent(hl);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		JsoupWiki.initSearch();

	}
}
