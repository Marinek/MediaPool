package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.Game;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class GameView extends AbstractEntityView<entity.Game> {

    @Override
    protected EntityEditor createForm() {
        return new GameForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Game";
    }

	public Class<Game> getEntityClass() {
        return Game.class;
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

	public List<Game> listEntities() {
        List<Game> list = Game.findAllGames();
        return list;
    }

	public Game saveEntity(Game entity) {
        if (entity == null) {
            return null;
        }
        return (Game) entity.merge();
    }

	public void deleteEntity(Game entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Game entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Game createEntityInstance() {
        return new Game();
    }

	public BeanContainer<Long, Game> getTableContainer() {
        BeanContainer<Long, Game> container = new BeanContainer<Long, Game>(Game.class);
        container.setBeanIdProperty("id");
        for (Game entity : Game.findAllGames()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Game entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Game>(entity);
        }
        return item;
    }

	public Game getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Game>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Game entity) {
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
