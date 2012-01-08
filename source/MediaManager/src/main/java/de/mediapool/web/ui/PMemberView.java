package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import entity.meta.PMember;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class PMemberView extends AbstractEntityView<entity.meta.PMember> {

    @Override
    protected EntityEditor createForm() {
        return new PMemberForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "P Member";
    }

	public Class<PMember> getEntityClass() {
        return PMember.class;
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

	public List<PMember> listEntities() {
        List<PMember> list = PMember.findAllPMembers();
        return list;
    }

	public PMember saveEntity(PMember entity) {
        if (entity == null) {
            return null;
        }
        return (PMember) entity.merge();
    }

	public void deleteEntity(PMember entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(PMember entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public PMember createEntityInstance() {
        return new PMember();
    }

	public BeanContainer<Long, PMember> getTableContainer() {
        BeanContainer<Long, PMember> container = new BeanContainer<Long, PMember>(PMember.class);
        container.setBeanIdProperty("id");
        for (PMember entity : PMember.findAllPMembers()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(PMember entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<PMember>(entity);
        }
        return item;
    }

	public PMember getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<PMember>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(PMember entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
}
