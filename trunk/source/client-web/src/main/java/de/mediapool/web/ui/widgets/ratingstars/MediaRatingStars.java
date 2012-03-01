package de.mediapool.web.ui.widgets.ratingstars;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

import de.mediapool.core.domain.MRating;
import de.mediapool.web.ui.view.MediaForm;

@SuppressWarnings("serial")
public class MediaRatingStars extends VerticalLayout {

	private TextArea description;
	private Label username;
	private RatingStars ratingStars;
	private MRating mrating;
	private Button saveRatingButton;
	private Button deleteRatingButton;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(5);

	static {
		valueCaptions.put(1, "Epic Fail");
		valueCaptions.put(2, "Poor");
		valueCaptions.put(3, "OK");
		valueCaptions.put(4, "Good");
		valueCaptions.put(5, "Excellent");
	}

	public MediaRatingStars(MediaForm form) {
		description = new TextArea();
		description.setImmediate(true);
		username = new Label();
		ratingStars = new RatingStars();
		saveRatingButton = new Button();
		saveRatingButton.addListener(form);
		saveRatingButton.setDescription("Save");
		saveRatingButton.setIcon(new ThemeResource("icons/new/16/save.png"));

		deleteRatingButton = new Button();
		deleteRatingButton.addListener(form);
		deleteRatingButton.setDescription("Delete");
		deleteRatingButton.setIcon(new ThemeResource("icons/new/16/cancel.png"));

		ratingStars.setMaxValue(5);
		ratingStars.setImmediate(true);
		ratingStars.setDescription("Your rating");
		ratingStars.setValueCaption(valueCaptions.values().toArray(new String[5]));
		addComponent(ratingStars);
		addComponent(username);
		addComponent(description);
		addComponent(saveRatingButton);
		addComponent(deleteRatingButton);

		ratingStars.addListener(new Property.ValueChangeListener() {

			public void valueChange(ValueChangeEvent event) {
				Double value = (Double) event.getProperty().getValue();

				getWindow().showNotification("You voted " + value + " stars for " + username.toString() + ".",
						Notification.TYPE_TRAY_NOTIFICATION);

				RatingStars changedRs = (RatingStars) event.getProperty();
				// reset value captions
				changedRs.setValueCaption(valueCaptions.values().toArray(new String[5]));
				// set "Your Rating" caption
				changedRs.setValueCaption((int) Math.round(value), "Your Rating");

			}
		});

		setEnabled(false);
	}

	public void setEnabled(boolean enabled) {
		description.setEnabled(enabled);
		ratingStars.setEnabled(enabled);
		deleteRatingButton.setEnabled(enabled);
		saveRatingButton.setEnabled(enabled);

	}

	public void refreshStars() {
		if (mrating != null) {
			setEnabled(true);
			description.setValue(mrating.getDescription());
			ratingStars.setValue(mrating.getVoting());
		}
	}

	public MRating bindAndGetRating() {
		mrating.setDescription((String) description.getValue());
		mrating.setVoting((Double) ratingStars.getValue());
		return mrating;
	}

	public void setMrating(MRating mrating) {
		this.mrating = mrating;
		refreshStars();
	}

}
