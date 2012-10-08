package de.mediapool.web.ui.view;

import com.vaadin.ui.Table;

import de.mediapool.web.ui.container.AbstractEntityBeanContainer;

public class AbstractEntityTableView extends Table {

	private static final long serialVersionUID = 1L;

	public AbstractEntityTableView(AbstractEntityBeanContainer movieItems) {
		this.setContainerDataSource(movieItems);
	}

}