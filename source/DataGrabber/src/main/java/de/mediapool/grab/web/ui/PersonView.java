package de.mediapool.grab.web.ui;

import de.mediapool.grab.web.AbstractEntityView;
import de.mediapool.grab.web.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = de.mediapool.grab.domain.Person.class)
public class PersonView extends AbstractEntityView<de.mediapool.grab.domain.Person> {

    @Override
    protected EntityEditor createForm() {
        return new PersonForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }

}
