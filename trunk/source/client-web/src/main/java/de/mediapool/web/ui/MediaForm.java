package de.mediapool.web.ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.Holding;
import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.Product;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.impl.MediaImage;

@Configurable
public class MediaForm extends VerticalLayout implements Button.ClickListener, FormFieldFactory {

	private static final long serialVersionUID = 1L;

	BeanItem<MediaInterface> item;
	private Form holdingForm;
	private Form productForm;
	private Form movieForm;

	private Button saveButton;
	private Button cancelButton;
	private Button addButton;
	private Button removeButton;

	private Label title = new Label();
	private Label subtitle = new Label();

	private boolean initialzied = false;

	private MediaImage image;

	List<String> knownvalues;
	List<String> situationvalues;

	@Autowired
	private MediaService mediaService;

	public MediaForm() {

		title.setStyleName("titleheader");

		setMargin(true, false, false, true);

		VerticalLayout productView = new VerticalLayout();
		HorizontalLayout formView = new HorizontalLayout();

		holdingForm = new BeanValidationForm<MovieHoldingEntry>(MovieHoldingEntry.class);
		productForm = new BeanValidationForm<MovieProductEntry>(MovieProductEntry.class);
		movieForm = new BeanValidationForm<MovieEntry>(MovieEntry.class);

		productForm.setFormFieldFactory(this);
		holdingForm.setFormFieldFactory(this);
		movieForm.setFormFieldFactory(this);

		holdingForm.setWriteThrough(false);
		holdingForm.setImmediate(true);

		saveButton = new Button("Save", this);
		cancelButton = new Button("Cancel", this);
		addButton = new Button("Add", this);
		removeButton = new Button("Remove", this);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(saveButton);
		footer.addComponent(cancelButton);
		footer.setVisible(false);
		holdingForm.setFooter(footer);

		image = new MediaImage();

		productView.addComponent(image);
		productView.addComponent(productForm);

		formView.addComponent(productView);
		formView.addComponent(new Label(" "));
		formView.addComponent(movieForm);
		formView.addComponent(new Label(" "));
		formView.addComponent(holdingForm);

		addComponent(title);
		addComponent(subtitle);
		addComponent(formView);
	}

	// TODO move in central Area of Application
	private void initFieldValues() {
		if (!initialzied) {
			knownvalues = getMediaService().getMpresetsFor("known");
			situationvalues = getMediaService().getMpresetsFor("situation");
			initialzied = true;
		}
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
			getWindow().showNotification("Saved Successfully", Notification.TYPE_HUMANIZED_MESSAGE);
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
		initFieldValues();
		String pid = (String) propertyId;
		Field field = DefaultFieldFactory.get().createField(item, propertyId, uiContext);
		if (pid.equals("description")) {
			TextArea descField = new TextArea();
			descField.setInputPrompt("Hint");
			descField.setHeight("200px");
			descField.setNullRepresentation("");
			return descField;
		}
		if (pid.equals("situation")) {
			ComboBox situationBox = new ComboBox("Situation");
			for (String sit : situationvalues) {
				situationBox.addItem(sit);
			}
			return situationBox;
		}
		if (pid.equals("knowm")) {
			ComboBox knownBox = new ComboBox("Known");
			for (String sit : knownvalues) {
				knownBox.addItem(sit);
			}
			return knownBox;
		}
		if (field instanceof TextField) {
			((TextField) field).setNullRepresentation("");
		}
		return field;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(BeanItem<MediaInterface> item) {
		this.item = item;
		refreshForm();

	}

	private void refreshForm() {
		Collection form_fields = Arrays.asList(new MovieHoldingEntry().form_fields());
		holdingForm.getFooter().setVisible(loggedIn());
		holdingForm.setItemDataSource(getItem(), form_fields);
		changeImage();
		setTitle();
	}

	// TODO find better way
	private boolean isHolding(BeanItem<MediaInterface> selectedItem) {
		String classname = "de.mediapool.core.domain.container.MovieHoldingEntry";
		String itemname = selectedItem.getBean().getClass().getName();
		return classname.equals(itemname);
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
			Holding holding;
			if (isHolding(selectedItem)) {
				holding = ((MovieHoldingEntry) selectedItem.getBean()).getHolding();
				setItem(selectedItem);
			} else {
				holding = new Holding();
				holding.setMuser(getMUser());
				holding.setProduct(product);
				MovieHoldingEntry movieHoldingEntry = new MovieHoldingEntry(holding);
				BeanItem<MediaInterface> holdingItem = new BeanItem<MediaInterface>(movieHoldingEntry);
				setItem(holdingItem);
			}
		} else {
			setItem(selectedItem);
		}

	}

	private void changeImage() {
		image.setMediaItem(item);
	}

	private void setTitle() {
		Property titleprop = item.getItemProperty("title");
		if (titleprop != null) {
			title.setValue((String) titleprop.getValue());
		}
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
