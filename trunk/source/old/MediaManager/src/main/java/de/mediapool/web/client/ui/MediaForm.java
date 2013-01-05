package de.mediapool.web.client.ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.vaadin.addon.customfield.PropertyConverter;
import org.vaadin.addon.customfield.beanfield.BeanSetFieldPropertyConverter;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import de.mediapool.web.client.WebEntityManagerView;
import entity.Media;
import entity.meta.Participation;
import entity.select.Award;
import entity.select.MLanguage;

@SuppressWarnings("serial")
public class MediaForm extends Form implements ClickListener {

	private Map<Object, PropertyConverter> converterMap = new LinkedHashMap<Object, PropertyConverter>();

	private Label errorMessageLabel = new Label();

	public Collection<Object> getBeanPropertyIds() {
		return Arrays.asList(new Object[] { "title", "originaltitle", "launchyear", "cover", "price", "ean",
				"description", "special", "contenttype", "genre", "carrier", "mlanguage", "award", "participation" });
	}

	// data item being edited
	private Item item;

	private Button save = new Button("Save", (ClickListener) this);
	private Button cancel = new Button("Cancel", (ClickListener) this);
	private Button edit = new Button("Edit", (ClickListener) this);
	private Button delete = new Button("Delete", (ClickListener) this);

	private WebEntityManagerView entityView;
	private boolean newEntityMode = false;
	private Media newMedia = null;

	public MediaForm(WebEntityManagerView entityView) {
		this.entityView = entityView;

		/*
		 * Enable buffering so that commit() must be called for the form before
		 * input is written to the data. (Form input is not written immediately
		 * through to the underlying object.)
		 */
		setWriteThrough(false);
		// configureConverters();
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		footer.addComponent(edit);
		footer.setVisible(false);

		setFooter(footer);

	}

	public void buttonClick(ClickEvent event) {
		Button source = event.getButton();

		if (source == save) {
			doCommit();
		} else if (source == cancel) {
			if (newEntityMode) {
				newEntityMode = false;
				/* Clear the form and make it invisible */
				setItemDataSource(null);
			} else {
				discard();
			}
			setReadOnly(true);
		} else if (source == edit) {
			setReadOnly(false);
		}
	}

	public boolean doCommit() {
		try {
			commit();
			if (newEntityMode) {
				/* We need to add the new person to the container */
				Item addedItem = entityView.getDataSource().addItem(newMedia);
				/*
				 * We must update the form to use the Item from our datasource
				 * as we are now in edit mode (no longer in add mode)
				 */
				setItemDataSource(addedItem);

				newEntityMode = false;
			}
			saveEntity(getEntityForItem());
			setReadOnly(true);
			return true;
		} catch (InvalidValueException e) {
			// show validation error also on the save button
			setCommitErrorMessage(e.getMessage());
			return false;
		}
	}

	public Media getEntityForItem() {
		if (getItemDataSource() != null) {
			return ((BeanItem<Media>) getItemDataSource()).getBean();
		} else {
			return null;
		}
	}

	public void commit() {
		if (getItemDataSource() != null) {
			// validateFields();
			// setCommitErrorMessage(null);
			commitFields();
		}
	}

	public void validateFields() {
		if (getItemDataSource() != null) {
			for (Object propertyId : getItemDataSource().getItemPropertyIds()) {
				Field field = getField(propertyId);
				if (field != null && !field.isReadOnly()) {
					field.validate();
				}
			}
		}
	}

	public void commitFields() {
		if (getItemDataSource() != null) {
			for (Object propertyId : getItemDataSource().getItemPropertyIds()) {
				Field field = getField(propertyId);
				if (field != null && !field.isReadOnly()) {
					field.commit();
				}
			}
		}
	}

	@Override
	public void setItemDataSource(Item newDataSource) {
		newEntityMode = false;

		if (newDataSource != null) {
			super.setItemDataSource(newDataSource);

			// setFieldValues(item);
			setCommitErrorMessage(null);

			setReadOnly(true);
			getFooter().setVisible(true);
		} else {
			super.setItemDataSource(null);
			getFooter().setVisible(false);
		}
	}

	public void setCommitErrorMessage(String message) {
		errorMessageLabel.setVisible(message != null);
		errorMessageLabel.setValue(message);
	}

	public PropertyConverter getConverter(Object propertyId) {
		return converterMap.get(propertyId);
	}

	public void setFieldPropertyDataSource(Object propertyId, Property property) {
		Field field = getField(propertyId);
		if (field == null) {
			return;
		}
		if (property == null) {
			field.setPropertyDataSource(null);
		} else {
			PropertyConverter converter = getConverter(propertyId);
			if (converter != null) {
				converter.setPropertyDataSource(property);
				field.setPropertyDataSource(converter);
			} else {
				if (field instanceof CheckBox && property.getValue() == null) {
					property.setValue(Boolean.FALSE);
				}
				field.setPropertyDataSource(property);
			}
		}
	}

	public void setFieldValues(Item item) {
		if (item != null) {
			// set values for fields in item
			for (Object propertyId : item.getItemPropertyIds()) {
				setFieldPropertyDataSource(propertyId, item.getItemProperty(propertyId));
			}
			// other fields are not touched by default
		} else {
			// reset all fields
			for (Object propertyId : getBeanPropertyIds()) {
				setFieldPropertyDataSource(propertyId, null);
			}
		}
	}

	public Media saveEntity(Media entity) {
		if (entity == null) {
			return null;
		}
		return (Media) entity.merge();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);
		save.setVisible(!readOnly);
		cancel.setVisible(!readOnly);
		edit.setVisible(readOnly);
	}

	public void addContact() {
		// Create a temporary item for the form
		newMedia = new Media();
		setItemDataSource(new BeanItem<entity.Media>(newMedia));
		newEntityMode = true;
		setReadOnly(false);
	}

	public void configureConverters() {
		// cannot parametrize PropertyConverter here due to an AJDT bug
		PropertyConverter converter;
		Container container;
		Field field;

		field = getField("mlanguage");
		if (field instanceof AbstractSelect) {
			container = ((AbstractSelect) field).getContainerDataSource();
			converter = new BeanSetFieldPropertyConverter<MLanguage, Long>(MLanguage.class, container, "id");
			converterMap.put("mlanguage", converter);
		}

		field = getField("participation");
		if (field instanceof AbstractSelect) {
			container = ((AbstractSelect) field).getContainerDataSource();
			converter = new BeanSetFieldPropertyConverter<Participation, Long>(Participation.class, container, "id");
			converterMap.put("participation", converter);
		}

		field = getField("award");
		if (field instanceof AbstractSelect) {
			container = ((AbstractSelect) field).getContainerDataSource();
			converter = new BeanSetFieldPropertyConverter<Award, Long>(Award.class, container, "id");
			converterMap.put("award", converter);
		}
	}

}