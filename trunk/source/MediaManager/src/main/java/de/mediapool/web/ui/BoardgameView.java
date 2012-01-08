package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.Boardgame;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class BoardgameView extends AbstractEntityView<entity.Boardgame> {

    @Override
    protected EntityEditor createForm() {
        return new BoardgameForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Boardgame";
    }

	public Class<Boardgame> getEntityClass() {
        return Boardgame.class;
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

	public List<Boardgame> listEntities() {
        List<Boardgame> list = Boardgame.findAllBoardgames();
        return list;
    }

	public Boardgame saveEntity(Boardgame entity) {
        if (entity == null) {
            return null;
        }
        return (Boardgame) entity.merge();
    }

	public void deleteEntity(Boardgame entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Boardgame entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Boardgame createEntityInstance() {
        return new Boardgame();
    }

	public BeanContainer<Long, Boardgame> getTableContainer() {
        BeanContainer<Long, Boardgame> container = new BeanContainer<Long, Boardgame>(Boardgame.class);
        container.setBeanIdProperty("id");
        for (Boardgame entity : Boardgame.findAllBoardgames()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Boardgame entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Boardgame>(entity);
        }
        return item;
    }

	public Boardgame getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Boardgame>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Boardgame entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("mlanguage");
        table.addGeneratedColumn("mlanguage", new EntityTableColumnGenerator((String) getMLanguageCaptionPropertyId()));
        table.removeGeneratedColumn("award");
        table.addGeneratedColumn("award", new EntityTableColumnGenerator((String) getAwardCaptionPropertyId()));
        table.removeGeneratedColumn("participation");
        table.addGeneratedColumn("participation", new EntityTableColumnGenerator((String) getParticipationCaptionPropertyId()));
    }

	public Object getMLanguageCaptionPropertyId() {
        return null;
    }

	public Object getAwardCaptionPropertyId() {
        return null;
    }

	public Object getParticipationCaptionPropertyId() {
        return null;
    }
}
