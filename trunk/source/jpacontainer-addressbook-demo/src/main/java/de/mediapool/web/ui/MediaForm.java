package de.mediapool.web.ui;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.service.MediaService;

@Configurable
public class MediaForm extends HorizontalLayout implements Button.ClickListener, FormFieldFactory {

	private static final long serialVersionUID = 1L;

	private Item item;
	private Form editorForm;
	private Button saveButton;
	private Button cancelButton;
	private Embedded image;

	@Autowired
	private MediaService mediaService;

	public MediaForm() {
		image = new Embedded("", new ThemeResource("cover/Cover.jpg"));
		image.setWidth("200px");
		addComponent(image);

		editorForm = new BeanValidationForm<MovieEntry>(MovieEntry.class);
		editorForm.setFormFieldFactory(this);
		editorForm.setWriteThrough(false);
		editorForm.setImmediate(true);

		saveButton = new Button("Save", this);
		cancelButton = new Button("Cancel", this);

		editorForm.getFooter().addComponent(saveButton);
		editorForm.getFooter().addComponent(cancelButton);
		addComponent(editorForm);

		setCaption(buildCaption());
	}

	/**
	 * @return the caption of the editor window
	 */
	private String buildCaption() {
		return "test";
		// String.format("%s %s", movieItem.getItemProperty("title").getValue(),
		// movieItem.getItemProperty("genre")
		// .getValue());
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
			getMediaService().saveMovieEntry(item);
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
		// if ("participation".equals(propertyId)) {
		// return new ParticipationSelector();
		// }

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

	public static final Object[] NATURAL_COL_ORDER = new Object[] { "title", "username", "carrier", "rating" };

	public void setItem(Item item) {
		// , Arrays.asList("title", "genre")
		editorForm.setItemDataSource(item, Arrays.asList(NATURAL_COL_ORDER));
		this.item = item;
		changeImage();

	}

	private void changeImage() {
		Property cover = item.getItemProperty("cover");
		if (cover != null) {
			String imageUrl = (String) cover.getValue();
			image.requestRepaint();
			image.setSource(new ThemeResource("cover/" + imageUrl));
		}

	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

}
