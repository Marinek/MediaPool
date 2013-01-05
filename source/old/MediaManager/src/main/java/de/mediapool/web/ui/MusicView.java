package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.Music;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class MusicView extends AbstractEntityView<entity.Music> {

    @Override
    protected EntityEditor createForm() {
        return new MusicForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Music";
    }

	public Class<Music> getEntityClass() {
        return Music.class;
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

	public List<Music> listEntities() {
        List<Music> list = Music.findAllMusics();
        return list;
    }

	public Music saveEntity(Music entity) {
        if (entity == null) {
            return null;
        }
        return (Music) entity.merge();
    }

	public void deleteEntity(Music entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Music entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Music createEntityInstance() {
        return new Music();
    }

	public BeanContainer<Long, Music> getTableContainer() {
        BeanContainer<Long, Music> container = new BeanContainer<Long, Music>(Music.class);
        container.setBeanIdProperty("id");
        for (Music entity : Music.findAllMusics()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Music entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Music>(entity);
        }
        return item;
    }

	public Music getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Music>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Music entity) {
        return entity != null ? entity.getId() : null;
    }

	public void setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("mlanguage");
        table.addGeneratedColumn("mlanguage", new EntityTableColumnGenerator((String) getMLanguageCaptionPropertyId()));
        table.removeGeneratedColumn("award");
        table.addGeneratedColumn("award", new EntityTableColumnGenerator((String) getAwardCaptionPropertyId()));
        table.removeGeneratedColumn("participation");
        table.addGeneratedColumn("participation", new EntityTableColumnGenerator((String) getParticipationCaptionPropertyId()));
        table.removeGeneratedColumn("tracks");
        table.addGeneratedColumn("tracks", new EntityTableColumnGenerator((String) getTrackCaptionPropertyId()));
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

	public Object getTrackCaptionPropertyId() {
        return null;
    }
}
