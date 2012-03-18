package de.mediapool.web.ui.widgets.ratingstars;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.MRating;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.web.ui.view.MediaForm;
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
		Set<MRating> mratings = movieEntry.getMratings();
		Set<MRating> otherrrating = new HashSet<MRating>();
		avstars.setAverageRating(movieEntry.getAverageRating());

		avstars.setMcaption("Total (" + mratings.size() + ") :");
		addComponent(avstars);
		userRatingStars = null;
		for (MRating mrating : mratings) {
			if (form.loggedIn() && mrating.getMuser().equals(form.getMUser())) {
				userRatingStars = new MediaRatingStars(this);
				userRatingStars.setMrating(mrating, RatingMode.SAVEDVIEW);
				addComponent(userRatingStars);
			} else {
				otherrrating.add(mrating);
			}

		}
		if (userRatingStars == null && form.loggedIn()) {
			MRating mrating = new MRating();
			mrating.setMuser(form.getMUser());
			mrating.setMedia(movieEntry.getMovie());
			mrating.setRatingDate(new Date());
			userRatingStars = new MediaRatingStars(this);
			userRatingStars.setMrating(mrating, RatingMode.NEWVIEW);
			addComponent(userRatingStars);
		}
		for (MRating mrating : otherrrating) {
			MediaRatingStars stars = new MediaRatingStars(this);

			stars.setMrating(mrating, RatingMode.OTHERVIEW);
			addComponent(stars);
		}
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
		MRating mrating = userRatingStars.bindAndGetRating();
		if ("Save".equals(event.getButton().getDescription())) {
			if (movieEntry.getMratings().contains(mrating)) {
				movieEntry.getMratings().remove(mrating);
			}
			MRating changed = form.getMediaService().addOrChangeRating(mrating);
			userRatingStars.setMrating(changed, RatingMode.SAVEDVIEW);
			movieEntry.getMratings().add(changed);
			fillRatingPanel();

		}

		if ("Delete".equals(event.getButton().getDescription())) {
			movieEntry.getMratings().remove(mrating);
			form.getMediaService().removeRating(mrating);
			fillRatingPanel();
		}
		if ("Edit".equals(event.getButton().getDescription())) {
			userRatingStars.refreshStars(RatingMode.EDITVIEW);
		}

	}

}
