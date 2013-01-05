package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.Media;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class MediaView extends AbstractEntityView<entity.Media> {

    @Override
    protected EntityEditor createForm() {
        return new MediaForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Media";
    }

	public Class<Media> getEntityClass() {
        return Media.class;
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

	public List<Media> listEntities() {
        List<Media> list = Media.findAllMedias();
        return list;
    }

	public Media saveEntity(Media entity) {
        if (entity == null) {
            return null;
        }
        return (Media) entity.merge();
    }

	public void deleteEntity(Media entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Media entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Media createEntityInstance() {
        return new Media();
    }

	public BeanContainer<Long, Media> getTableContainer() {
        BeanContainer<Long, Media> container = new BeanContainer<Long, Media>(Media.class);
        container.setBeanIdProperty("id");
        for (Media entity : Media.findAllMedias()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Media entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Media>(entity);
        }
        return item;
    }

	public Media getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Media>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Media entity) {
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
