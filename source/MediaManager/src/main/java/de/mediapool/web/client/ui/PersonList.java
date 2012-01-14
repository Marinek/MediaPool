package de.mediapool.web.client.ui;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

import de.mediapool.web.client.WebEntityManagerView;
import de.mediapool.web.client.data.Person;
import de.mediapool.web.client.data.PersonContainer;

@SuppressWarnings("serial")
public class PersonList extends Table {
	public PersonList(WebEntityManagerView entityView) {
		setSizeFull();
		setContainerDataSource(entityView.getDataSource());

		setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
		setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);

		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		/*
		 * Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		setSelectable(true);
		setImmediate(true);
		addListener((ValueChangeListener) entityView);
		/* We don't want to allow users to de-select a row */
		setNullSelectionAllowed(false);

		// customize email column to have mailto: links using column generator
		addGeneratedColumn("email", new ColumnGenerator() {
			public Component generateCell(Table source, Object itemId, Object columnId) {
				Person p = (Person) itemId;
				Link l = new Link();
				l.setResource(new ExternalResource("mailto:" + p.getEmail()));
				l.setCaption(p.getEmail());
				return l;
			}
		});
	}

}