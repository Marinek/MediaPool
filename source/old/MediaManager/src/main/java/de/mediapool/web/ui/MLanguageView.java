package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import entity.select.MLanguage;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class MLanguageView extends AbstractEntityView<entity.select.MLanguage> {

    @Override
    protected EntityEditor createForm() {
        return new MLanguageForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "M Language";
    }

	public Class<MLanguage> getEntityClass() {
        return MLanguage.class;
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

	public List<MLanguage> listEntities() {
        List<MLanguage> list = MLanguage.findAllMLanguages();
        return list;
    }

	public MLanguage saveEntity(MLanguage entity) {
        if (entity == null) {
            return null;
        }
        return (MLanguage) entity.merge();
    }

	public void deleteEntity(MLanguage entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(MLanguage entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public MLanguage createEntityInstance() {
        return new MLanguage();
    }

	public BeanContainer<Long, MLanguage> getTableContainer() {
        BeanContainer<Long, MLanguage> container = new BeanContainer<Long, MLanguage>(MLanguage.class);
        container.setBeanIdProperty("id");
        for (MLanguage entity : MLanguage.findAllMLanguages()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(MLanguage entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<MLanguage>(entity);
        }
        return item;
    }

	public MLanguage getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<MLanguage>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(MLanguage entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
}
