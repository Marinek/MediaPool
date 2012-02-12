package de.mediapool.web.ui.adding;

import java.util.Arrays;

import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import de.mediapool.core.domain.Holding;
import de.mediapool.core.domain.container.MovieProductEntry;

@SuppressWarnings("serial")
public class NewHoldingForm extends HorizontalLayout implements ClickListener, FormFieldFactory {

	private Object[] form_fields = new Object[] { "knowm", "since", "rating", "visible", "lastUsed", "situation",
			"inventoryplace", "inventorynumber" };

	private Button saveButton;
	private Button cancelButton;
	private BeanValidationForm<Holding> form;

	public NewHoldingForm(BeanItem<MovieProductEntry> productItem) {

		form = new BeanValidationForm<Holding>(Holding.class);
		Holding holding = new Holding();
		form.setItemDataSource(new BeanItem<Holding>(holding), Arrays.asList(form_fields));
		addComponent(form);

		MovieEntryDetailView productView = new MovieEntryDetailView(productItem);
		productView.setMargin(false, true, false, false);
		addComponent(productView);

		form.setFormFieldFactory(this);
		form.setWriteThrough(false);
		form.setImmediate(true);

		saveButton = new Button("Save", (ClickListener) this);
		cancelButton = new Button("Cancel", this);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(saveButton);
		footer.addComponent(cancelButton);
		footer.setVisible(false);

		form.setFooter(footer);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == saveButton) {
			form.commit();
		} else if (event.getButton() == cancelButton) {
			form.discard();
		}
	}

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		Field field = DefaultFieldFactory.get().createField(item, propertyId, uiContext);
		if (field instanceof TextField) {
			((TextField) field).setNullRepresentation("");
		}
		return field;
	}

}
