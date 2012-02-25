package de.mediapool.web.ui.widgets;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;

@SuppressWarnings("serial")
public class MediaAccordion extends HorizontalLayout implements Accordion.SelectedTabChangeListener {

	private static final ThemeResource icon1 = new ThemeResource("icons/new/16/hands.png");
	private static final ThemeResource icon2 = new ThemeResource("icons/new/16/people.png");
	private static final ThemeResource icon3 = new ThemeResource("icons/new/16/product.png");

	private Accordion a;

	public MediaAccordion() {
		setSpacing(true);
		MediaTree tree = new MediaTree();

		Label l1 = new Label("- keine Listen");
		Label l2 = new Label("- keine Freunde");
		Label l3 = new Label("- keine Medien");

		a = new Accordion();
		a.setHeight("580px");
		a.setWidth("200px");

		a.addTab(tree, "Listen", icon1);
		a.addTab(l2, "Freunde", icon2);
		a.addTab(l3, "Medien", icon3);
		a.addListener(this);

		addComponent(a);
	}

	public void selectedTabChange(SelectedTabChangeEvent event) {
		TabSheet tabsheet = event.getTabSheet();
		Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
		if (tab != null) {
			getWindow().showNotification("Selected tab: " + tab.getCaption());
		}
	}
}
