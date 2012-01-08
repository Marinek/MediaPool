package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.meta.Participation;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class ParticipationView extends AbstractEntityView<entity.meta.Participation> {

    @Override
    protected EntityEditor createForm() {
        return new ParticipationForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Participation";
    }

	public Class<Participation> getEntityClass() {
        return Participation.class;
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

	public List<Participation> listEntities() {
        List<Participation> list = Participation.findAllParticipations();
        return list;
    }

	public Participation saveEntity(Participation entity) {
        if (entity == null) {
            return null;
        }
        return (Participation) entity.merge();
    }

	public void deleteEntity(Participation entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Participation entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Participation createEntityInstance() {
        return new Participation();
    }

	public BeanContainer<Long, Participation> getTableContainer() {
        BeanContainer<Long, Participation> container = new BeanContainer<Long, Participation>(Participation.class);
        container.setBeanIdProperty("id");
        for (Participation entity : Participation.findAllParticipations()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Participation entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Participation>(entity);
        }
        return item;
    }

	public Participation getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Participation>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Participation entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("pmember");
        table.addGeneratedColumn("pmember", new EntityTableColumnGenerator((String) getPMemberCaptionPropertyId()));
    }

	public Object getPMemberCaptionPropertyId() {
        return "name";
    }
}
