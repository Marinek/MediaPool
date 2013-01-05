package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import entity.user.MUser;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class MUserView extends AbstractEntityView<entity.user.MUser> {

    @Override
    protected EntityEditor createForm() {
        return new MUserForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "M User";
    }

	public Class<MUser> getEntityClass() {
        return MUser.class;
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

	public List<MUser> listEntities() {
        List<MUser> list = MUser.findAllMUsers();
        return list;
    }

	public MUser saveEntity(MUser entity) {
        if (entity == null) {
            return null;
        }
        return (MUser) entity.merge();
    }

	public void deleteEntity(MUser entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(MUser entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public MUser createEntityInstance() {
        return new MUser();
    }

	public BeanContainer<Long, MUser> getTableContainer() {
        BeanContainer<Long, MUser> container = new BeanContainer<Long, MUser>(MUser.class);
        container.setBeanIdProperty("id");
        for (MUser entity : MUser.findAllMUsers()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(MUser entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<MUser>(entity);
        }
        return item;
    }

	public MUser getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<MUser>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(MUser entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
}
