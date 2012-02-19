package de.mediapool.web.ui;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.Holding;
import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.MediaInterface;
import de.mediapool.core.domain.Product;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.impl.MediaImage;

@Configurable
public class MediaForm extends HorizontalLayout implements Button.ClickListener, FormFieldFactory {

	private static final long serialVersionUID = 1L;

	private Item item;
	private Form holdingForm;
	private Form productForm;
	private Form movieForm;

	private Button saveButton;
	private Button cancelButton;
	private MediaImage image;

	@Autowired
	private MediaService mediaService;

	public MediaForm() {

		// Class itemClass = mediaView.getBeanItems().getBeanType();
		// itemClass.getClass();
		setMargin(true, false, false, true);

		VerticalLayout productView = new VerticalLayout();

		holdingForm = new BeanValidationForm<MovieHoldingEntry>(MovieHoldingEntry.class);
		productForm = new BeanValidationForm<MovieProductEntry>(MovieProductEntry.class);
		movieForm = new BeanValidationForm<MovieEntry>(MovieEntry.class);

		holdingForm.setFormFieldFactory(this);
		holdingForm.setWriteThrough(false);
		holdingForm.setImmediate(true);

		saveButton = new Button("Save", this);
		cancelButton = new Button("Cancel", this);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(saveButton);
		footer.addComponent(cancelButton);
		footer.setVisible(false);

		image = new MediaImage();

		productView.addComponent(image);
		productView.addComponent(productForm);

		holdingForm.setFooter(footer);

		addComponent(productView);
		addComponent(new Label(" "));
		addComponent(movieForm);
		addComponent(new Label(" "));
		addComponent(holdingForm);

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
			holdingForm.commit();
			getMediaService().saveMovieHoldingEntry(item);
			// fireEvent(new EditorSavedEvent(this, item));
		} else if (event.getButton() == cancelButton) {
			holdingForm.discard();
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		holdingForm.getFooter().setVisible(loggedIn());
		this.item = item;
		changeImage();

	}

	public void setBeanItem(BeanItem<MediaInterface> selectedItem) {
		Product product = ((MovieProductEntry) selectedItem.getBean()).getProduct();
		BeanItem<MovieProductEntry> productItem = new BeanItem<MovieProductEntry>(new MovieProductEntry(product));
		BeanItem<MovieEntry> movieItem = new BeanItem<MovieEntry>(new MovieEntry(product.getMovie()));
		productForm.setItemDataSource(productItem, Arrays.asList(new MovieProductEntry().form_fields()));
		movieForm.setItemDataSource(movieItem, Arrays.asList(new MovieEntry().form_fields()));
		productForm.setReadOnly(true);
		movieForm.setReadOnly(true);

		if (loggedIn()) {
			Holding holding = ((MovieHoldingEntry) selectedItem.getBean()).getHolding();
			if (holding == null) {
				holding = new Holding();
				holding.setMuser(getMUser());
				holding.setProduct(product);
				MovieHoldingEntry movieHoldingEntry = new MovieHoldingEntry(holding);
				BeanItem<MovieHoldingEntry> holdingItem = new BeanItem<MovieHoldingEntry>(movieHoldingEntry);
				holdingForm.setItemDataSource(holdingItem, Arrays.asList(movieHoldingEntry.form_fields()));
				setItem(holdingItem);
			} else {
				holdingForm.setItemDataSource(selectedItem, Arrays.asList(new MovieHoldingEntry().form_fields()));
				setItem(selectedItem);
			}
		} else {
			setItem(selectedItem);
		}

	}

	private void changeImage() {
		Boolean localItem = false;
		Property cover = item.getItemProperty("cover");
		Property local = item.getItemProperty("local");

		if (local != null) {
			localItem = (Boolean) local.getValue();
		}
		if (cover == null) {
			cover = item.getItemProperty("image");
		}
		Property title = item.getItemProperty("title");
		image.setFilename(nullCheck(cover), nullCheck(title), localItem);

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

	private MUser getMUser() {
		return (MUser) getApplication().getUser();
	}

	private boolean loggedIn() {
		return getMUser() != null;
	}

	/*
	 * unused ?
	 * 
	 * public void addListener(EditorSavedListener listener) { try { Method
	 * method = EditorSavedListener.class.getDeclaredMethod("editorSaved", new
	 * Class[] { EditorSavedEvent.class }); addListener(EditorSavedEvent.class,
	 * listener, method); } catch (final java.lang.NoSuchMethodException e) { //
	 * This should never happen throw new
	 * java.lang.RuntimeException("Internal error, editor saved method not found"
	 * ); } }
	 * 
	 * public void removeListener(EditorSavedListener listener) {
	 * removeListener(EditorSavedEvent.class, listener); }
	 * 
	 * public static class EditorSavedEvent extends Component.Event {
	 * 
	 * private static final long serialVersionUID = 1L; private Item savedItem;
	 * 
	 * public EditorSavedEvent(Component source, Item savedItem) {
	 * super(source); this.savedItem = savedItem; }
	 * 
	 * public Item getSavedItem() { return savedItem; } }
	 * 
	 * public interface EditorSavedListener extends Serializable { public void
	 * editorSaved(EditorSavedEvent event); }
	 */
}
