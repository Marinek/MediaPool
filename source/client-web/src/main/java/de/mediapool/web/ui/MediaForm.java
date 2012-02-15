package de.mediapool.web.ui;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import de.mediapool.core.domain.MediaInterface;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.impl.MediaImage;

@Configurable
public class MediaForm extends HorizontalLayout implements Button.ClickListener, FormFieldFactory {

	private static final long serialVersionUID = 1L;

	private Item item;
	private Form editorForm;
	private Button saveButton;
	private Button cancelButton;
	private MediaImage image;
	private Object[] formfields;

	GridLayout grid;

	@Autowired
	private MediaService mediaService;

	public MediaForm(boolean readOnly, MediaView mediaView, Object[] formfields) {

		// Class itemClass = mediaView.getBeanItems().getBeanType();
		// itemClass.getClass();
		grid = new GridLayout(2, 2);
		addComponent(grid);

		editorForm = new BeanValidationForm<MediaInterface>(MediaInterface.class);
		editorForm.setFormFieldFactory(this);
		editorForm.setWriteThrough(false);
		editorForm.setImmediate(true);
		editorForm.setReadOnly(true);
		this.formfields = formfields;

		saveButton = new Button("Save", this);
		cancelButton = new Button("Cancel", this);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(saveButton);
		footer.addComponent(cancelButton);
		footer.setVisible(false);

		image = new MediaImage();

		grid.addComponent(image, 0, 0);
		grid.addComponent(editorForm, 1, 0);

		editorForm.setFooter(footer);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.
	 * ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == saveButton) {
			editorForm.commit();
			// getMediaService().saveMovieEntry(item);
			// fireEvent(new EditorSavedEvent(this, item));
		} else if (event.getButton() == cancelButton) {
			editorForm.discard();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.FormFieldFactory#createField(com.vaadin.data.Item,
	 * java.lang.Object, com.vaadin.ui.Component)
	 */
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		Field field = DefaultFieldFactory.get().createField(item, propertyId, uiContext);
		if (field instanceof TextField) {
			((TextField) field).setNullRepresentation("");
		}
		return field;
	}

	public void addListener(EditorSavedListener listener) {
		try {
			Method method = EditorSavedListener.class.getDeclaredMethod("editorSaved",
					new Class[] { EditorSavedEvent.class });
			addListener(EditorSavedEvent.class, listener, method);
		} catch (final java.lang.NoSuchMethodException e) {
			// This should never happen
			throw new java.lang.RuntimeException("Internal error, editor saved method not found");
		}
	}

	public void removeListener(EditorSavedListener listener) {
		removeListener(EditorSavedEvent.class, listener);
	}

	public static class EditorSavedEvent extends Component.Event {

		private static final long serialVersionUID = 1L;
		private Item savedItem;

		public EditorSavedEvent(Component source, Item savedItem) {
			super(source);
			this.savedItem = savedItem;
		}

		public Item getSavedItem() {
			return savedItem;
		}
	}

	public interface EditorSavedListener extends Serializable {
		public void editorSaved(EditorSavedEvent event);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		editorForm.setItemDataSource(item, Arrays.asList(formfields));
		editorForm.getFooter().setVisible(true);
		this.item = item;
		changeImage();

	}

	private void changeImage() {
		Property cover = item.getItemProperty("cover");
		Property title = item.getItemProperty("title");
		image.setFilename(nullCheck(cover), nullCheck(title));

	}

	private String nullCheck(Property check) {
		String string = "";
		if (check != null) {
			string = (String) check.getValue();
		}
		return string;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

}
