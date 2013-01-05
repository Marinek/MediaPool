package de.mediapool.web.client.ui;

import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class ListView extends VerticalSplitPanel {
	public ListView(MediaList personList, MediaForm personForm) {
		addStyleName("view");
		setFirstComponent(personList);
		setSecondComponent(personForm);
		setSplitPosition(40);
	}
}