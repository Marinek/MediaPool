package de.mediapool.web.ui.widgets.ratingstars;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.web.ui.view.MediaForm;
import de.mediapool.web.ui.widgets.ConfirmationDialog;
import de.mediapool.web.ui.widgets.ratingstars.MediaRatingStars.RatingMode;

@SuppressWarnings("serial")
public class MediaRatingStarsPanel extends VerticalLayout implements ClickListener {

	private MovieEntry movieEntry;
	private MediaForm form;
	private MediaRatingStars userRatingStars;

	public MediaRatingStarsPanel(MediaForm form) {
		this.form = form;
	}

	private void fillRatingPanel() {
		removeAllComponents();
		MediaRatingStars avstars = new MediaRatingStars(this);
		// Set<MRating> mratings = movieEntry.getMratings();
		// Set<MRating> otherrrating = new HashSet<MRating>();
		// avstars.setAverageRating(movieEntry.getAverageRating());
		//
		// avstars.setMcaption("Total (" + mratings.size() + ") :");
		// addComponent(avstars);
		// userRatingStars = null;
		// for (MRating mrating : mratings) {
		// if (form.loggedIn() && mrating.getMuser().equals(form.getMUser())) {
		// userRatingStars = new MediaRatingStars(this);
		// userRatingStars.setMrating(mrating, RatingMode.SAVEDVIEW);
		// addComponent(userRatingStars);
		// } else {
		// otherrrating.add(mrating);
		// }
		//
		// }
		// if (userRatingStars == null && form.loggedIn()) {
		// MRating mrating = new MRating();
		// mrating.setMuser(form.getMUser());
		// mrating.setMedia(movieEntry.getMovie());
		// mrating.setRatingDate(new Date());
		// userRatingStars = new MediaRatingStars(this);
		// userRatingStars.setMrating(mrating, RatingMode.NEWVIEW);
		// addComponent(userRatingStars);
		// }
		// for (MRating mrating : otherrrating) {
		// MediaRatingStars stars = new MediaRatingStars(this);
		//
		// stars.setMrating(mrating, RatingMode.OTHERVIEW);
		// addComponent(stars);
		// }
	}

	public MovieEntry getMovieEntry() {
		return movieEntry;
	}

	public void setMovieEntry(MovieEntry movieEntry) {
		this.movieEntry = movieEntry;
		fillRatingPanel();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if ("Save".equals(event.getButton().getDescription())) {
			// MRating mrating = userRatingStars.bindAndGetRating();
			// if (movieEntry.getMratings().contains(mrating)) {
			// movieEntry.getMratings().remove(mrating);
			// }
			// MRating changed =
			// form.getMediaService().addOrChangeRating(mrating);
			// userRatingStars.setMrating(changed, RatingMode.SAVEDVIEW);
			// movieEntry.getMratings().add(changed);
			// fillRatingPanel();

		}

		if ("Delete".equals(event.getButton().getDescription())) {
			askFirst();
		}
		if ("Edit".equals(event.getButton().getDescription())) {
			userRatingStars.refreshStars(RatingMode.EDITVIEW);
		}

	}

	private void askFirst() {
		getWindow().addWindow(new ConfirmationDialog("Really remove ?", "Are you sure to remove this Rating", "Yes", "No", new ConfirmationDialog.ConfirmationDialogCallback() {
			public void response(boolean remove) {
				if (remove) {
					deleteRating();
				}
			}
		}));
	}

	private void deleteRating() {
		// MRating mrating = userRatingStars.bindAndGetRating();
		// movieEntry.getMratings().remove(mrating);
		// form.getMediaService().removeRating(mrating);
		fillRatingPanel();
	}

}
