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

import de.mediapool.core.domain.MRating;

@SuppressWarnings("serial")
public class MediaRatingStars extends VerticalLayout {

	private TextArea description;
	private Label mcaption;
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

	public MediaRatingStars(MediaRatingStarsPanel panel) {
		description = new TextArea();
		description.setNullRepresentation("");
		description.setImmediate(true);
		mcaption = new Label();
		ratingStars = new RatingStars();
		saveRatingButton = new Button();
		saveRatingButton.addListener(panel);
		saveRatingButton.setDescription("Save");
		saveRatingButton.setIcon(new ThemeResource("icons/new/16/save.png"));

		deleteRatingButton = new Button();
		deleteRatingButton.addListener(panel);
		deleteRatingButton.setDescription("Delete");
		deleteRatingButton.setIcon(new ThemeResource("icons/new/16/cancel.png"));

		ratingStars.setMaxValue(5);
		ratingStars.setImmediate(true);
		ratingStars.setDescription("Your rating");
		ratingStars.setValueCaption(valueCaptions.values().toArray(new String[5]));
		addComponent(mcaption);
		addComponent(ratingStars);
		addComponent(description);
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(saveRatingButton);
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

		setEnabled(false);
	}

	public void setAverageRating(Double value) {
		description.setVisible(false);
		deleteRatingButton.setVisible(false);
		saveRatingButton.setVisible(false);
		ratingStars.setValue(value);
		ratingStars.setReadOnly(true);

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
			mcaption.setValue(mrating.getMuser().getUsername());
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

	public String getMcaption() {
		return (String) mcaption.getValue();
	}

	public void setMcaption(String caption) {
		this.mcaption.setValue(caption);
	}

}
