package de.mediapool.web.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Product;
import de.mediapool.core.service.MediaService;
import de.mediapool.core.service.grab.DataGrabber;

@SuppressWarnings("serial")
@Configurable
public class NewMediaForm extends VerticalLayout implements ClickListener, ValueChangeListener {

	@Autowired
	private DataGrabber datagrabber;

	@Autowired
	private MediaService mediaService;

	private List<Movie> movieList;
	private List<Product> productList;

	private OptionGroup select;

	private Button searchButton;
	private Button nextButton;
	private TextField searchField;

	private MediaMainView main;

	private GridLayout grid;

	public NewMediaForm(MediaMainView main) {
		this.main = main;
		select = new OptionGroup("Gefundene Filme :");
		select.setMultiSelect(false);
		select.setNullSelectionAllowed(false);

		grid = new GridLayout(4, 1);

		searchField = new TextField("Search");
		addComponent(searchField);

		searchButton = new Button("Search");
		addComponent(searchButton);

		searchButton.addListener((ClickListener) this);
		select.addListener((ValueChangeListener) this);
		select.setVisible(false);
		addComponent(select);

		nextButton = new Button("next");
		nextButton.setVisible(true);
		nextButton.addListener((ClickListener) this);
		addComponent(grid);
		addComponent(nextButton);
		setImmediate(true);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == searchButton) {
			productList = getDatagrabber().searchMovieProducts((String) searchField.getValue(), "Blu-ray");
			// movieList = getDatagrabber().searchMovie((String)
			// searchField.getValue(), false);
			// fillMovieSelect();
			fillProductSelect();
			showProductImages();
		}
		if (source == nextButton) {
			addProduct((Product) select.getValue());
		}

	}

	private void addProduct(Product product) {
		if (main.loggedin()) {
			getMediaService().addProductToUser(product, main.getMUser());
		}
	}

	private void fillMovieSelect() {
		for (Movie movie : movieList) {
			String toString = movie.getTitle() + ", " + movie.getLaunchyear() + ", " + movie.getMlanguage();
			select.addItem(toString);
		}
		select.setVisible(true);

	}

	private void fillProductSelect() {
		for (Product product : productList) {
			StringBuffer str = new StringBuffer();
			str.append(product.getMovie().getTitle());
			str.append(", ");
			str.append(product.getMovie().getLaunchyear());
			str.append(", ");
			str.append(product.getMlanguage());
			str.append(", ");
			str.append(product.getCarrier());
			str.append(", ");
			str.append(product.getPrice());
			select.addItem(product);
		}
		select.setVisible(true);

	}

	private void showProductImages() {
		int row = 0;
		int column = 0;
		for (int i = 0; i < productList.size(); i++) {
			Embedded em = new Embedded("", new ExternalResource(productList.get(i).getCover()));
			em.setWidth("100px");
			if ((i + 1) % 4 == 0) {
				row++;
				column = 0;
				grid.insertRow(row);
			}
			grid.addComponent(em, column, row);
			column++;
		}
	}

	public DataGrabber getDatagrabber() {
		return datagrabber;
	}

	public void setDatagrabber(DataGrabber datagrabber) {
		this.datagrabber = datagrabber;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		nextButton.setVisible(true);

	}

}
