package de.mediapool.web.ui.adding;

import java.util.Iterator;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.container.MovieProductEntry;

@SuppressWarnings("serial")
public class MovieEntryDetailListView extends VerticalLayout {

	private BeanItemContainer<MovieProductEntry> productList;

	private GridLayout grid;

	public MovieEntryDetailListView() {
		setImmediate(true);
		grid = new GridLayout(4, 1);
		grid.setImmediate(true);
		grid.setWidth("100%");
		addComponent(grid);
	}

	public void fillView(BeanItemContainer<MovieProductEntry> productList) {
		setProductList(productList);
		fillGrid();
		requestRepaint();
	}

	public BeanItemContainer<MovieProductEntry> getCheckedProducts() {
		BeanItemContainer<MovieProductEntry> checkedProducts = new BeanItemContainer<MovieProductEntry>(
				MovieProductEntry.class);
		Iterator<Component> it = grid.getComponentIterator();
		while (it.hasNext()) {
			MovieEntryDetailView view = (MovieEntryDetailView) it.next();
			if (view.isChecked()) {
				checkedProducts.addItem(view.getProductItem());
			}
		}
		return checkedProducts;
	}

	public BeanItem<MovieProductEntry> getCheckedProduct() {

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
		for (MovieProductEntry productEntry : productList.getItemIds()) {
			VerticalLayout vl = new MovieEntryDetailView(productList.getItem(productEntry), true);
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

	public BeanItemContainer<MovieProductEntry> getProductList() {
		return productList;
	}

	public void setProductList(BeanItemContainer<MovieProductEntry> productList) {
		this.productList = productList;
	}

}
