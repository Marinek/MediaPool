package com.vaadin.demo.jpaaddressbook;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Item;
import com.vaadin.demo.jpaaddressbook.domain.Movie;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class PersonEditor extends Window implements Button.ClickListener, FormFieldFactory {

	private final Item personItem;
	private Form editorForm;
	private Button saveButton;
	private Button cancelButton;

	public PersonEditor(Item personItem) {
		this.personItem = personItem;
		editorForm = new BeanValidationForm<Movie>(Movie.class);
		editorForm.setFormFieldFactory(this);
		editorForm.setWriteThrough(false);
		editorForm.setImmediate(true);
		editorForm.setItemDataSource(personItem, Arrays.asList("title", "genre"));

		saveButton = new Button("Save", this);
		cancelButton = new Button("Cancel", this);

		editorForm.getFooter().addComponent(saveButton);
		editorForm.getFooter().addComponent(cancelButton);
		getContent().setSizeUndefined();
		addComponent(editorForm);
		setCaption(buildCaption());
	}

	/**
	 * @return the caption of the editor window
	 */
	private String buildCaption() {
		return String.format("%s %s", personItem.getItemProperty("title").getValue(),
				personItem.getItemProperty("genre").getValue());
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
			fireEvent(new EditorSavedEvent(this, personItem));
		} else if (event.getButton() == cancelButton) {
			editorForm.discard();
		}
		close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.FormFieldFactory#createField(com.vaadin.data.Item,
	 * java.lang.Object, com.vaadin.ui.Component)
	 */
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		if ("department".equals(propertyId)) {
			return new DepartmentSelector();
		}

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

}
