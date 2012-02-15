package de.mediapool.web.ui.adding;

import java.util.Iterator;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.MediaInterface;
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

	public BeanItemContainer<MediaInterface> getCheckedProducts() {
		BeanItemContainer<MediaInterface> checkedProducts = new BeanItemContainer<MediaInterface>(MediaInterface.class);
		Iterator<Component> it = grid.getComponentIterator();
		while (it.hasNext()) {
			MovieEntryDetailView view = (MovieEntryDetailView) it.next();
			if (view.isChecked()) {
				checkedProducts.addItem(view.getProductItem());
			}
		}
		return checkedProducts;
	}

	public BeanItem<MediaInterface> getCheckedProduct() {

		Iterator<Component> it = grid.getComponentIterator();
		while (it.hasNext()) {
			MovieEntryDetailView view = (MovieEntryDetailView) it.next();
			if (view.isChecked()) {
				return view.getProductItem();
			}
		}
		return null;
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

	public BeanItemContainer<MediaInterface> getProductList() {
		return view.getBeanItems();
	}

}
