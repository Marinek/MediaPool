package de.mediapool.web.ui;

import de.mediapool.web.AbstractEntityView;
import de.mediapool.web.EntityEditor;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.Book;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class BookView extends AbstractEntityView<entity.Book> {

    @Override
    protected EntityEditor createForm() {
        return new BookForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }


	public String getEntityName() {
        return "Book";
    }

	public Class<Book> getEntityClass() {
        return Book.class;
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

	public List<Book> listEntities() {
        List<Book> list = Book.findAllBooks();
        return list;
    }

	public Book saveEntity(Book entity) {
        if (entity == null) {
            return null;
        }
        return (Book) entity.merge();
    }

	public void deleteEntity(Book entity) {
        if (entity != null) {
            entity.remove();
        }
    }

	public boolean isNewEntity(Book entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }

	public String getIdProperty() {
        return "id";
    }

	public String getVersionProperty() {
        return "version";
    }

	public Book createEntityInstance() {
        return new Book();
    }

	public BeanContainer<Long, Book> getTableContainer() {
        BeanContainer<Long, Book> container = new BeanContainer<Long, Book>(Book.class);
        container.setBeanIdProperty("id");
        for (Book entity : Book.findAllBooks()) {
            container.addBean(entity);
        }
        return container;
    }

	public Item getItemForEntity(Book entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Book>(entity);
        }
        return item;
    }

	public Book getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Book>) item).getBean();
        } else {
            return null;
        }
    }

	public Object getIdForEntity(Book entity) {
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
