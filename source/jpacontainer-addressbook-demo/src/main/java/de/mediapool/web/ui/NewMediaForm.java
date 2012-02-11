package de.mediapool.web.ui;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Product;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.core.service.MediaService;

@SuppressWarnings("serial")
@Configurable
public class NewMediaForm extends VerticalLayout implements ClickListener, ValueChangeListener {

	@Autowired
	private MediaService mediaService;

	private List<Movie> movieList;
	private BeanItemContainer<MovieProductEntry> productList;

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
		searchButton.focus();
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

		setSpacing(true);
		setMargin(true, false, false, true);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == searchButton) {
			productList = getMediaService().searchMovieProducts((String) searchField.getValue(), "Blu-ray");
			// movieList = getDatagrabber().searchMovie((String)
			// searchField.getValue(), false);
			// fillMovieSelect();
			fillProductSelect();
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
		int row = 0;
		int column = 0;
		int counter = 1;
		for (MovieProductEntry productEntry : productList.getItemIds()) {
			VerticalLayout vl = productDetailView(productList.getItem(productEntry));
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

	private VerticalLayout productDetailView(BeanItem<MovieProductEntry> productItem) {
		VerticalLayout view = new VerticalLayout();
		Property cover = productItem.getItemProperty("cover");
		if (cover != null && cover.getValue() != null) {
			Embedded em = new Embedded("", new ExternalResource((String) cover.getValue()));
			em.setWidth("100px");
			view.addComponent(em);
		}
		Form mpeForm = new Form();
		mpeForm.setImmediate(true);
		mpeForm.setReadOnly(true);
		mpeForm.setItemDataSource(productItem, Arrays.asList(new MovieProductEntry().form_fields()));

		view.addComponent(mpeForm);
		view.addComponent(new com.vaadin.ui.CheckBox());
		return view;
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
