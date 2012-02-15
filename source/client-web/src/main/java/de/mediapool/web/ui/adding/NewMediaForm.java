package de.mediapool.web.ui.adding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.MediaInterface;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.MediaMainView;

@SuppressWarnings("serial")
@Configurable
public class NewMediaForm extends VerticalLayout implements ClickListener, ValueChangeListener {

	@Autowired
	private MediaService mediaService;

	// private List<Movie> movieList;
	// private BeanItemContainer<MovieProductEntry> productList;

	private MovieEntryDetailListView detailView;

	private Button searchButton;
	private Button nextButton;
	private TextField searchField;

	private MediaMainView main;

	public NewMediaForm(MediaMainView main) {
		this.main = main;

		searchField = new TextField("Search");
		addComponent(searchField);

		searchButton = new Button("Search");
		searchButton.focus();
		addComponent(searchButton);

		searchButton.addListener((ClickListener) this);

		nextButton = new Button("next");
		nextButton.setVisible(true);
		nextButton.addListener((ClickListener) this);
		// detailView = new MovieEntryDetailListView();

		// addComponent(detailView);
		addComponent(nextButton);
		setImmediate(true);

		setSpacing(true);
		setMargin(true, false, false, true);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == searchButton) {
			searchButton.setComponentError(null);
			// productList = getMediaService().searchMovieProducts((String)
			// searchField.getValue(), "Blu-ray");
			detailView.fillView();
		}
		if (source == nextButton) {
			switchToHolding();
		}

	}

	private void switchToHolding() {
		removeAllComponents();
		BeanItem<MediaInterface> productItem = detailView.getCheckedProduct();
		// BeanItemContainer<MediaInterface> productItems =
		// detailView.getCheckedProducts();

		addComponent(new NewHoldingForm(productItem));
	}

	// private void addProduct(Product product) {
	// if (main.loggedin()) {
	// getMediaService().addProductToUser(product, main.getMUser());
	// }
	// }

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
