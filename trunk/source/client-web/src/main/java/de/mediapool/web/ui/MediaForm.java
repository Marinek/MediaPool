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
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
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
import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Product;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.domain.container.MovieEntryType;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.impl.MediaImage;

@Configurable
public class MediaForm extends VerticalLayout implements Button.ClickListener, FormFieldFactory {

	private static final long serialVersionUID = 1L;

	private BeanItem<MediaInterface> item;
	private Form holdingForm;
	private Form productForm;
	private Form movieForm;

	private Button saveButton;
	private Button cancelButton;
	private Button addButton;
	private Button removeButton;

	private HorizontalLayout formtoolbar;

	private Label title = new Label();
	private Label subtitle = new Label();

	private boolean initialzied = false;

	private boolean owned = false;

	private MediaImage image;

	List<String> knownvalues;
	List<String> situationvalues;

	@Autowired
	private MediaService mediaService;

	public MediaForm() {
		createFormToolbar();
		title.setStyleName("titleheader");

		// margin right
		setMargin(false, false, false, true);

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

		image = new MediaImage();

		productView.setMargin(false, true, false, true);
		productView.addComponent(image);
		productView.addComponent(productForm);

		formView.addComponent(holdingForm);
		formView.addComponent(productView);
		formView.addComponent(movieForm);

		addComponent(formtoolbar);
		addComponent(title);
		addComponent(subtitle);
		addComponent(formView);
	}

	private void createFormToolbar() {

		formtoolbar = new HorizontalLayout();
		formtoolbar.setMargin(true, false, true, false);
		saveButton = new Button();
		saveButton.addListener(this);
		saveButton.setDescription("Save");
		saveButton.setIcon(new ThemeResource("icons/new/16/save.png"));
		saveButton.setEnabled(false);

		cancelButton = new Button();
		cancelButton.addListener(this);
		cancelButton.setDescription("Cancel");
		cancelButton.setIcon(new ThemeResource("icons/new/16/cancel.png"));
		cancelButton.setEnabled(false);

		addButton = new Button();
		addButton.addListener(this);
		addButton.setDescription("Add");
		addButton.setIcon(new ThemeResource("icons/new/16/add.png"));
		addButton.setEnabled(false);

		removeButton = new Button();
		removeButton.addListener(this);
		removeButton.setDescription("Delete");
		removeButton.setIcon(new ThemeResource("icons/new/16/trash.png"));
		removeButton.setEnabled(false);

		formtoolbar.addComponent(saveButton);
		formtoolbar.addComponent(cancelButton);
		formtoolbar.addComponent(addButton);
		formtoolbar.addComponent(removeButton);
		formtoolbar.setWidth("100%");

		formtoolbar.setExpandRatio(removeButton, 1);
		formtoolbar.setComponentAlignment(removeButton, Alignment.TOP_RIGHT);

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
			owned = true;
			checkButtons();
			// fireEvent(new EditorSavedEvent(this, item));
		} else if (event.getButton() == cancelButton) {
			holdingForm.discard();
		} else if (event.getButton() == removeButton) {
			owned = false;
			checkButtons();

		} else if (event.getButton() == addButton) {
			addProduct();
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

	public BeanItem<MediaInterface> getItem() {
		return item;
	}

	public void setItem(BeanItem<MediaInterface> item) {
		this.item = item;
		refreshForm();

	}

	private void refreshForm() {
		changeImage();
		setTitle();
		checkButtons();
	}

	public void setBeanItem(BeanItem<MediaInterface> selectedItem, MovieEntryType type) {
		switch (type) {
		case MOVIEHOLDINGENTRY:
			initMovieHoldingEntry(selectedItem);
			break;
		case MOVIEENTRY:
			initMovieEntry(selectedItem);
			break;
		case MOVIEPRODUCTENTRY:
			initMovieProductEntry(selectedItem);
			break;
		}
		setItem(selectedItem);
	}

	private void initMovieEntry(BeanItem<MediaInterface> selectedItem) {
		Movie movie = ((MovieEntry) selectedItem.getBean()).getMovie();
		BeanItem<MovieEntry> movieItem = new BeanItem<MovieEntry>(new MovieEntry(movie));
		movieForm.setItemDataSource(movieItem, Arrays.asList(new MovieEntry().form_fields()));
		movieForm.setReadOnly(true);
		holdingForm.setVisible(loggedIn());
	}

	private void initMovieHoldingEntry(BeanItem<MediaInterface> selectedItem) {
		if (loggedIn()) {
			MovieHoldingEntry movieHoldingEntry = ((MovieHoldingEntry) selectedItem.getBean());
			Holding holding = movieHoldingEntry.getHolding();
			Collection form_fields = Arrays.asList(movieHoldingEntry.form_fields());
			owned = holding.getMuser().equals(getMUser());
			holdingForm.setReadOnly(owned);
			holdingForm.setItemDataSource(selectedItem, form_fields);
		}
		initMovieProductEntry(selectedItem);

	}

	private void initMovieProductEntry(BeanItem<MediaInterface> selectedItem) {
		Product product = ((MovieProductEntry) selectedItem.getBean()).getProduct();
		BeanItem<MovieProductEntry> productItem = new BeanItem<MovieProductEntry>(new MovieProductEntry(product));
		productForm.setItemDataSource(productItem, Arrays.asList(new MovieProductEntry().form_fields()));
		productForm.setReadOnly(true);
		initMovieEntry(selectedItem);
	}

	private void checkButtons() {
		addButton.setEnabled(false);
		removeButton.setEnabled(false);
		cancelButton.setEnabled(false);
		saveButton.setEnabled(false);

		if (loggedIn()) {
			if (owned) {
				removeButton.setEnabled(true);
				saveButton.setEnabled(true);
				cancelButton.setEnabled(true);
			} else {
				addButton.setEnabled(true);
			}
		}

	}

	private void addProduct() {
		Product product = ((MovieProductEntry) getItem().getBean()).getProduct();
		Holding holding = new Holding();
		holding.setMuser(getMUser());
		holding.setProduct(product);
		MovieHoldingEntry movieHoldingEntry = new MovieHoldingEntry(holding);
		BeanItem<MediaInterface> holdingItem = new BeanItem<MediaInterface>(movieHoldingEntry);
		initMovieHoldingEntry(holdingItem);
		setItem(holdingItem);
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
