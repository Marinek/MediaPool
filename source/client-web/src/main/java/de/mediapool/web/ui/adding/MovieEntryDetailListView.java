package de.mediapool.web.ui.adding;

import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.container.MovieContainer;
import de.mediapool.web.ui.MediaView;

@SuppressWarnings("serial")
public class MovieEntryDetailListView extends VerticalLayout {

	private GridLayout grid;
	private MediaView view;

	public MovieEntryDetailListView(MediaView view) {
		this.view = view;
		setImmediate(true);
		grid = new GridLayout(4, 1);
		grid.setImmediate(true);
		grid.setMargin(true, false, false, true);
		addComponent(grid);
		fillView();
	}

	public void fillView() {
		fillGrid();
		requestRepaint();
	}

	private void fillGrid() {
		grid.removeAllComponents();
		int row = 0;
		int column = 0;
		int counter = 1;
		for (MediaInterface entry : getProductList().getItemIds()) {
			VerticalLayout vl = new MovieEntryDetailView(getProductList().getItem(entry), true);
			vl.addListener((LayoutClickListener) view);
			if (counter % 4 == 0) {
				row++;
				column = 0;
				grid.insertRow(row);
			}
			grid.addComponent(vl, column, row);
			column++;
			counter++;
		}

	}

	public MovieContainer getProductList() {
		return view.getMovieItems();
	}

	private boolean loggedIn() {
		return getMUser() != null;
	}

	public MUser getMUser() {
		return (MUser) getApplication().getUser();
	}

}
