package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import entity.select.Award;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class AwardView extends AbstractEntityView<entity.select.Award> {

    @Override
    protected EntityEditor createForm() {
        return new AwardForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Award";
    }

	public Class<Award> getEntityClass() {
        return Award.class;
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

	public List<Award> listEntities() {
        List<Award> list = Award.findAllAwards();
        return list;
    }

	public Award saveEntity(Award entity) {
        if (entity == null) {
            return null;
        }
        return (Award) entity.merge();
    }

	public void deleteEntity(Award entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Award entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Award createEntityInstance() {
        return new Award();
    }

	public BeanContainer<Long, Award> getTableContainer() {
        BeanContainer<Long, Award> container = new BeanContainer<Long, Award>(Award.class);
        container.setBeanIdProperty("id");
        for (Award entity : Award.findAllAwards()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Award entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Award>(entity);
        }
        return item;
    }

	public Award getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Award>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Award entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
}
