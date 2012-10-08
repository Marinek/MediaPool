package de.mediapool.web.ui.view;

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
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.domain.container.MovieEntryType;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.elements.MediaImage;
import de.mediapool.web.ui.widgets.ConfirmationDialog;
import de.mediapool.web.ui.widgets.ratingstars.MediaRatingStarsPanel;

@Configurable
public class MediaForm extends VerticalLayout implements Button.ClickListener, FormFieldFactory {

	private static final long serialVersionUID = 1L;

	private BeanItem<MediaInterface> mediaItem;
	private Form holdingForm;
	private Form productForm;
	private Form movieForm;

	private Button saveButton;
	private Button cancelButton;
	private Button addButton;
	private Button removeButton;

	private HorizontalLayout formtoolbar;
	private MediaRatingStarsPanel mratingStars;

	private Label title = new Label();
	private Label subtitle = new Label();

	private boolean initialzied = false;
	private boolean inHolding = false;
	private boolean inDB = false;

	private MediaImage image;

	private List<String> knownvalues;
	private List<String> situationvalues;

	private MediaView view;
	private MovieEntryType entryType;

	@Autowired
	private MediaService mediaService;

	public MediaForm(MediaView view) {
		this.view = view;
		createFormToolbar();
		title.setStyleName("titleheader");

		// margin right
		setMargin(false, false, false, true);

		VerticalLayout productView = new VerticalLayout();
		HorizontalLayout formView = new HorizontalLayout();
		VerticalLayout editArea = new VerticalLayout();

		holdingForm = new BeanValidationForm<MovieHoldingEntry>(MovieHoldingEntry.class);
		productForm = new BeanValidationForm<MovieProductEntry>(MovieProductEntry.class);
		movieForm = new BeanValidationForm<MovieEntry>(MovieEntry.class);

		productForm.setFormFieldFactory(this);
		holdingForm.setFormFieldFactory(this);
		movieForm.setFormFieldFactory(this);

		holdingForm.setWriteThrough(false);
		holdingForm.setImmediate(true);

		image = new MediaImage();

		editArea.addComponent(holdingForm);
		mratingStars = new MediaRatingStarsPanel(this);
		mratingStars.setVisible(false);
		editArea.addComponent(mratingStars);

		productView.setMargin(false, true, false, true);
		productView.addComponent(image);
		productView.addComponent(productForm);

		formView.addComponent(editArea);
		formView.addComponent(productView);
		formView.addComponent(movieForm);

		addComponent(formtoolbar);
		addComponent(title);
		addComponent(subtitle);
		addComponent(formView);
	}

	private void createFormToolbar() {

		formtoolbar = new HorizontalLayout();
		formtoolbar.setMargin(true, false, true, true);
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
			// knownvalues = getMediaService().getMpresetsFor("known");
			// situationvalues = getMediaService().getMpresetsFor("situation");
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
			saveProduct();
		} else if (event.getButton() == cancelButton) {
			holdingForm.discard();
		} else if (event.getButton() == removeButton) {
			askFirst();
		} else if (event.getButton() == addButton) {
			addHoldingToProduct();
		}
	}

	private void askFirst() {
		getWindow().addWindow(new ConfirmationDialog("Really remove ?", "Are you sure to remove this item", "Yes", "No", new ConfirmationDialog.ConfirmationDialogCallback() {
			public void response(boolean remove) {
				deleteProduct(remove);
			}
		}));
	}

	private void saveProduct() {
		holdingForm.commit();
		// mediaItem = getMediaService().saveMovieHoldingEntry(mediaItem);
		initMovieHoldingEntry(mediaItem);
		getWindow().showNotification("Saved Successfully", Notification.TYPE_HUMANIZED_MESSAGE);
		inDB = true;
		refreshButtons();
	}

	private void deleteProduct(boolean remove) {
		if (remove) {
			// getMediaService().removeMovieHoldingEntry(mediaItem);
			inHolding = false;
			view.getMovieItems().removeItem(mediaItem.getBean());
			refreshForm();
			getWindow().showNotification("Removed Successfully", Notification.TYPE_HUMANIZED_MESSAGE);
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
			descField.setHeight("150px");
			descField.setWidth("400px");
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
		if (pid.equals("known")) {
			ComboBox knownBox = new ComboBox("Known");
			for (String sit : knownvalues) {
				knownBox.addItem(sit);
			}
			return knownBox;
		}
		if (pid.equals("since")) {
			PopupDateField sinceDate = new PopupDateField("Since");
			sinceDate.setDateFormat("dd.MM.yyyy");
			sinceDate.setResolution(PopupDateField.RESOLUTION_DAY);
			sinceDate.setImmediate(true);
			return sinceDate;
		}
		if (pid.equals("lastUsed")) {
			PopupDateField usedDate = new PopupDateField("Last Used");
			usedDate.setDateFormat("dd.MM.yyyy");
			usedDate.setResolution(PopupDateField.RESOLUTION_DAY);
			usedDate.setImmediate(true);
			return usedDate;
		}
		if (field instanceof TextField) {
			((TextField) field).setNullRepresentation("");
		}
		return field;
	}

	public void setMediaItem(BeanItem<MediaInterface> selectedItem, MovieEntryType type) {
		this.entryType = type;
		switch (entryType) {
		case MOVIEHOLDINGENTRY:
			initMovieHoldingEntry(selectedItem);
			break;
		case MOVIEENTRY:
			initMovieEntry(selectedItem);
			inHolding = false;
			break;
		case MOVIEPRODUCTENTRY:
			initMovieProductEntry(selectedItem);
			inHolding = false;
			break;
		}
		mediaItem = selectedItem;
		refreshForm();
	}

	private void initMovieEntry(BeanItem<MediaInterface> selectedItem) {
		// MovieEntry entry = (MovieEntry) selectedItem.getBean();
		// // Movie movie = entry.getMovie();
		// BeanItem<MovieEntry> movieItem = new BeanItem<MovieEntry>(new
		// MovieEntry(movie));
		// movieForm.setItemDataSource(movieItem, Arrays.asList(new
		// MovieEntry().form_fields()));
		// movieForm.setReadOnly(true);
		// holdingForm.setVisible(loggedIn());
		// mratingStars.setMovieEntry(entry);
	}

	private void initMovieHoldingEntry(BeanItem<MediaInterface> selectedItem) {
		// if (loggedIn()) {
		// MovieHoldingEntry movieHoldingEntry = ((MovieHoldingEntry)
		// selectedItem.getBean());
		// // Holding holding = movieHoldingEntry.getHolding();
		// Collection form_fields =
		// Arrays.asList(movieHoldingEntry.form_fields());
		// // inDB = holding.getId() != null;
		// // inHolding = holding.getMuser().equals(getMUser());
		// holdingForm.setReadOnly(inHolding);
		// holdingForm.setItemDataSource(selectedItem, form_fields);
		// }
		// initMovieProductEntry(selectedItem);

	}

	private void initMovieProductEntry(BeanItem<MediaInterface> selectedItem) {
		// Product product = ((MovieProductEntry)
		// selectedItem.getBean()).getProduct();
		// BeanItem<MovieProductEntry> productItem = new
		// BeanItem<MovieProductEntry>(new MovieProductEntry(product));
		// productForm.setItemDataSource(productItem, Arrays.asList(new
		// MovieProductEntry().form_fields()));
		// productForm.setReadOnly(true);
		// initMovieEntry(selectedItem);
	}

	private void addHoldingToProduct() {
		// Product product;
		// switch (entryType) {
		// case MOVIEENTRY:
		// product = new Product();
		// break;
		// default:
		// product = ((MovieProductEntry)
		// getMediaItem().getBean()).getProduct();
		// break;
		// }
		//
		// Holding holding = new Holding();
		// holding.setMuser(getMUser());
		// holding.setProduct(product);
		// holding.setSince(new Date());
		// holding.setSituation("new");
		// holding.setKnown("unknown");
		// holding.setVisible(true);
		// MovieHoldingEntry movieHoldingEntry = new MovieHoldingEntry(holding);
		// BeanItem<MediaInterface> holdingItem = new
		// BeanItem<MediaInterface>(movieHoldingEntry);
		// initMovieHoldingEntry(holdingItem);
		// mediaItem = holdingItem;
		refreshForm();
	}

	public BeanItem<MediaInterface> getMediaItem() {
		return mediaItem;
	}

	private void refreshForm() {
		changeImage();
		setTitle();
		refreshButtons();
		refreshHolding();
		mratingStars.setVisible(mediaItem != null);
	}

	private void refreshHolding() {
		holdingForm.setVisible(inHolding);
	}

	private void refreshButtons() {
		addButton.setEnabled(false);
		removeButton.setEnabled(false);
		cancelButton.setEnabled(false);
		saveButton.setEnabled(false);

		// if (loggedIn()) {
		// if (inHolding) {
		// saveButton.setEnabled(true);
		// cancelButton.setEnabled(true);
		// removeButton.setEnabled(inDB);
		// } else {
		// addButton.setEnabled(true);
		// }
		// }

	}

	private void changeImage() {
		image.setMediaItem(mediaItem);
	}

	private void setTitle() {
		Property titleprop = mediaItem.getItemProperty("title");
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

	// public MUser getMUser() {
	// return (MUser) getApplication().getUser();
	// }
	//
	// public boolean loggedIn() {
	// return getMUser() != null;
	// }

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
