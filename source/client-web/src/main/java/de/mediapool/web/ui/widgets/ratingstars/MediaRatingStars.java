package de.mediapool.web.ui.widgets.ratingstars;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MediaRatingStars extends VerticalLayout {

	private TextArea description;
	private Label mcaption;
	private RatingStars ratingStars;
	// private MRating mrating;
	private Button saveRatingButton;
	private Button editRatingButton;
	private Button deleteRatingButton;

	private RatingMode mode;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(5);

	static {
		valueCaptions.put(1, "Epic Fail");
		valueCaptions.put(2, "Poor");
		valueCaptions.put(3, "OK");
		valueCaptions.put(4, "Good");
		valueCaptions.put(5, "Excellent");
	}

	public MediaRatingStars(MediaRatingStarsPanel panel) {
		description = new TextArea();
		description.setNullRepresentation("");
		description.setImmediate(true);
		mcaption = new Label();
		ratingStars = new RatingStars();
		ratingStars.setDescription(ratingStars.getValue().toString());

		saveRatingButton = new Button();
		saveRatingButton.setImmediate(true);
		saveRatingButton.addListener(panel);
		saveRatingButton.setDescription("Save");
		saveRatingButton.setIcon(new ThemeResource("icons/new/16/save.png"));

		deleteRatingButton = new Button();
		deleteRatingButton.setImmediate(true);
		deleteRatingButton.addListener(panel);
		deleteRatingButton.setDescription("Delete");
		deleteRatingButton.setIcon(new ThemeResource("icons/new/16/cancel.png"));

		editRatingButton = new Button();
		editRatingButton.setImmediate(true);
		editRatingButton.addListener(panel);
		editRatingButton.setDescription("Edit");
		editRatingButton.setIcon(new ThemeResource("icons/new/16/edit.png"));

		ratingStars.setMaxValue(5);
		ratingStars.setImmediate(true);
		ratingStars.setDescription("Your rating");
		ratingStars.setValueCaption(valueCaptions.values().toArray(new String[5]));

		addComponent(mcaption);
		addComponent(ratingStars);
		addComponent(description);
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(saveRatingButton);
		buttons.addComponent(editRatingButton);
		buttons.addComponent(deleteRatingButton);
		addComponent(buttons);

		ratingStars.addListener(new Property.ValueChangeListener() {

			public void valueChange(ValueChangeEvent event) {
				Double value = (Double) event.getProperty().getValue();

				// getWindow().showNotification("You voted " + value +
				// " stars for " + ".",
				// Notification.TYPE_TRAY_NOTIFICATION);

				RatingStars changedRs = (RatingStars) event.getProperty();
				// reset value captions
				changedRs.setValueCaption(valueCaptions.values().toArray(new String[5]));
				// set "Your Rating" caption
				changedRs.setValueCaption((int) Math.round(value), "Your Rating");

			}
		});

	}

	public void setAverageRating(Double value) {
		ratingStars.setReadOnly(false);
		ratingStars.setValue(value);
		ratingStars.setReadOnly(true);
		mode = RatingMode.AVERAGEVIEW;
		setViewForMode();

	}

	public void setWriteLock(boolean read) {
		description.setReadOnly(read);
		ratingStars.setReadOnly(read);
	}

	private void showButtons(boolean show) {
		deleteRatingButton.setVisible(show);
		saveRatingButton.setVisible(show);
		editRatingButton.setVisible(show);
	}

	public void refreshStars(RatingMode mode) {
		this.mode = mode;
		// if (mrating != null) {
		// setDescriptionValue(mrating.getDescription());
		// setRatingStarsValue(mrating.getVoting());
		// SimpleDateFormat sdf = new SimpleDateFormat();
		// sdf.applyPattern("dd.MM.yyyy");
		// String fdate = sdf.format(mrating.getRatingDate());
		// mcaption.setValue(mrating.getMuser().getUsername() + " (" + fdate +
		// ")");
		// setViewForMode();
		// }
	}

	private void setDescriptionValue(String value) {
		boolean remember = description.isReadOnly();
		description.setReadOnly(false);
		description.setValue(value);
		description.setReadOnly(remember);
	}

	private void setRatingStarsValue(Double value) {
		boolean remember = ratingStars.isReadOnly();
		ratingStars.setReadOnly(false);
		ratingStars.setValue(value);
		ratingStars.setReadOnly(remember);
	}

	private void setViewForMode() {
		switch (mode) {
		case NEWVIEW:
			setWriteLock(false);
			editRatingButton.setEnabled(false);
			saveRatingButton.setEnabled(true);
			deleteRatingButton.setEnabled(false);
			showButtons(true);
			// addCaption("new");
			break;
		case EDITVIEW:
			setWriteLock(false);
			editRatingButton.setEnabled(false);
			saveRatingButton.setEnabled(true);
			deleteRatingButton.setEnabled(true);
			showButtons(true);
			// addCaption("edit");
			break;
		case SAVEDVIEW:
			setWriteLock(true);
			showButtons(true);
			editRatingButton.setEnabled(true);
			saveRatingButton.setEnabled(false);
			deleteRatingButton.setEnabled(true);
			// addCaption("saved");
			break;
		case AVERAGEVIEW:
			description.setVisible(false);
			showButtons(false);
			// addCaption("aver");
			break;
		case OTHERVIEW:
			setWriteLock(true);
			showButtons(false);
			// addCaption("other");
			break;
		}

	}

	// private void addCaption(String value) {
	// String old = (String) mcaption.getValue();
	// mcaption.setValue(old + " " + value);
	// }

	// public MRating bindAndGetRating() {
	// mrating.setDescription((String) description.getValue());
	// mrating.setVoting((Double) ratingStars.getValue());
	// return mrating;
	// }
	//
	// public void setMrating(MRating mrating, RatingMode mode) {
	// this.mrating = mrating;
	// refreshStars(mode);
	// }

	public String getMcaption() {
		return (String) mcaption.getValue();
	}

	public void setMcaption(String caption) {
		this.mcaption.setValue(caption);
	}

	public enum RatingMode {
		EDITVIEW, NEWVIEW, SAVEDVIEW, AVERAGEVIEW, OTHERVIEW
	}

}
