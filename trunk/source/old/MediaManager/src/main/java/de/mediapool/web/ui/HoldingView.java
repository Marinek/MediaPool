package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.user.Holding;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class HoldingView extends AbstractEntityView<entity.user.Holding> {

    @Override
    protected EntityEditor createForm() {
        return new HoldingForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Holding";
    }

	public Class<Holding> getEntityClass() {
        return Holding.class;
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

	public List<Holding> listEntities() {
        List<Holding> list = Holding.findAllHoldings();
        return list;
    }

	public Holding saveEntity(Holding entity) {
        if (entity == null) {
            return null;
        }
        return (Holding) entity.merge();
    }

	public void deleteEntity(Holding entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Holding entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Holding createEntityInstance() {
        return new Holding();
    }

	public BeanContainer<Long, Holding> getTableContainer() {
        BeanContainer<Long, Holding> container = new BeanContainer<Long, Holding>(Holding.class);
        container.setBeanIdProperty("id");
        for (Holding entity : Holding.findAllHoldings()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Holding entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Holding>(entity);
        }
        return item;
    }

	public Holding getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Holding>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Holding entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("media");
        table.addGeneratedColumn("media", new EntityTableColumnGenerator((String) getMediaCaptionPropertyId()));
        table.removeGeneratedColumn("muser");
        table.addGeneratedColumn("muser", new EntityTableColumnGenerator((String) getMUserCaptionPropertyId()));
    }

	public Object getMediaCaptionPropertyId() {
        return null;
    }

	public Object getMUserCaptionPropertyId() {
        return null;
    }
}
