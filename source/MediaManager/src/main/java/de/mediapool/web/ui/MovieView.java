package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.Movie;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class MovieView extends AbstractEntityView<entity.Movie> {

    @Override
    protected EntityEditor createForm() {
        return new MovieForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Movie";
    }

	public Class<Movie> getEntityClass() {
        return Movie.class;
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

	public List<Movie> listEntities() {
        List<Movie> list = Movie.findAllMovies();
        return list;
    }

	public Movie saveEntity(Movie entity) {
        if (entity == null) {
            return null;
        }
        return (Movie) entity.merge();
    }

	public void deleteEntity(Movie entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Movie entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Movie createEntityInstance() {
        return new Movie();
    }

	public BeanContainer<Long, Movie> getTableContainer() {
        BeanContainer<Long, Movie> container = new BeanContainer<Long, Movie>(Movie.class);
        container.setBeanIdProperty("id");
        for (Movie entity : Movie.findAllMovies()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Movie entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Movie>(entity);
        }
        return item;
    }

	public Movie getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Movie>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Movie entity) {
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
