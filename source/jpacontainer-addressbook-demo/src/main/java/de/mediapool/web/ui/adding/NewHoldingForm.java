package de.mediapool.web.ui.adding;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

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
import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.MediaInterface;
import de.mediapool.core.domain.Product;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.service.MediaService;

@Configurable
@SuppressWarnings("serial")
public class NewHoldingForm extends HorizontalLayout implements ClickListener, FormFieldFactory {

	private Object[] form_fields = new Object[] { "knowm", "since", "rating", "visible", "lastUsed", "situation",
			"inventoryplace", "inventorynumber" };

	private Button saveButton;
	private Button cancelButton;
	private BeanValidationForm<MovieHoldingEntry> form;
	@Autowired
	private MediaService mediaService;

	private Item item;

	public NewHoldingForm(BeanItem<MediaInterface> productItem) {

		Product product = ((MovieProductEntry) productItem.getBean()).getProduct();
		Holding holding = new Holding();
		MUser muser = new MUser();
		holding.setMuser(muser);
		holding.setProduct(product);

		BeanItem<MovieHoldingEntry> holdingItem = new BeanItem<MovieHoldingEntry>(new MovieHoldingEntry(holding));

		form = new BeanValidationForm<MovieHoldingEntry>(MovieHoldingEntry.class);

		setItem(holdingItem);

		addComponent(form);

		MovieEntryDetailView productView = new MovieEntryDetailView(productItem);
		productView.setMargin(false, true, false, false);
		addComponent(productView);

		form.setFormFieldFactory((FormFieldFactory) this);
		form.setWriteThrough(false);
		form.setImmediate(true);

		saveButton = new Button("Save", (ClickListener) this);
		cancelButton = new Button("Cancel", this);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(saveButton);
		footer.addComponent(cancelButton);
		footer.setVisible(true);

		form.setFooter(footer);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == saveButton) {
			form.commit();
			getMediaService().saveMovieHoldingEntry(getItem());
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

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		form.setItemDataSource(item, Arrays.asList(form_fields));
		form.getFooter().setVisible(true);
		this.item = item;

	}

}
