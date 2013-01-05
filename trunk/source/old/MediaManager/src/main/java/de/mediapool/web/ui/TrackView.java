package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import entity.meta.Track;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class TrackView extends AbstractEntityView<entity.meta.Track> {

    @Override
    protected EntityEditor createForm() {
        return new TrackForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Track";
    }

	public Class<Track> getEntityClass() {
        return Track.class;
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

	public List<Track> listEntities() {
        List<Track> list = Track.findAllTracks();
        return list;
    }

	public Track saveEntity(Track entity) {
        if (entity == null) {
            return null;
        }
        return (Track) entity.merge();
    }

	public void deleteEntity(Track entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Track entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Track createEntityInstance() {
        return new Track();
    }

	public BeanContainer<Long, Track> getTableContainer() {
        BeanContainer<Long, Track> container = new BeanContainer<Long, Track>(Track.class);
        container.setBeanIdProperty("id");
        for (Track entity : Track.findAllTracks()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Track entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Track>(entity);
        }
        return item;
    }

	public Track getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Track>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Track entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
}
