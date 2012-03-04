package de.mediapool.web.ui.widgets.ratingstars;

import java.util.HashSet;
import java.util.Set;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.MRating;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.web.ui.view.MediaForm;

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
		avstars.setMcaption("Total :");
		addComponent(avstars);
		userRatingStars = null;
		for (MRating mrating : mratings) {
			if (form.loggedIn() && mrating.getMuser().equals(form.getMUser())) {
				userRatingStars = new MediaRatingStars(this);
				userRatingStars.setMrating(mrating);
				addComponent(userRatingStars);
			} else {
				otherrrating.add(mrating);
			}

		}
		if (userRatingStars == null && form.loggedIn()) {
			MRating mrating = new MRating();
			mrating.setMuser(form.getMUser());
			mrating.setMedia(movieEntry.getMovie());
			userRatingStars = new MediaRatingStars(this);
			userRatingStars.setMrating(mrating);
			addComponent(userRatingStars);
		}
		for (MRating mrating : otherrrating) {
			MediaRatingStars stars = new MediaRatingStars(this);

			stars.setMrating(mrating);
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
			if (!movieEntry.getMratings().contains(mrating)) {
				movieEntry.getMratings().add(mrating);
				form.getMediaService().addRating(movieEntry);
			} else {
				form.getMediaService().changeRating(mrating);
			}
		}
		if ("Delete".equals(event.getButton().getDescription())) {
			movieEntry.getMratings().remove(mrating);
			form.getMediaService().deleteRating(mrating);
		}
		fillRatingPanel();

	}

}
