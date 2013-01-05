package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import entity.select.Contenttype;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class ContenttypeView extends AbstractEntityView<entity.select.Contenttype> {

    @Override
    protected EntityEditor createForm() {
        return new ContenttypeForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Contenttype";
    }

	public Class<Contenttype> getEntityClass() {
        return Contenttype.class;
    }

	public boolean isCreateAllowed() {
        return true;
    }

	public boolean isUpdateAllowed() {
        return true;
    }

	public boolean isDeleteAllowed() {
        return true;
    }

	public List<Contenttype> listEntities() {
        List<Contenttype> list = Contenttype.findAllContenttypes();
        return list;
    }

	public Contenttype saveEntity(Contenttype entity) {
        if (entity == null) {
            return null;
        }
        return (Contenttype) entity.merge();
    }

	public void deleteEntity(Contenttype entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Contenttype entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Contenttype createEntityInstance() {
        return new Contenttype();
    }

	public BeanContainer<Long, Contenttype> getTableContainer() {
        BeanContainer<Long, Contenttype> container = new BeanContainer<Long, Contenttype>(Contenttype.class);
        container.setBeanIdProperty("id");
        for (Contenttype entity : Contenttype.findAllContenttypes()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Contenttype entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Contenttype>(entity);
        }
        return item;
    }

	public Contenttype getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Contenttype>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Contenttype entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
}
