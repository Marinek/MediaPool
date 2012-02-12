package de.mediapool.web.ui.adding;

import java.util.Arrays;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.container.MovieProductEntry;

@SuppressWarnings("serial")
public class MovieEntryDetailView extends VerticalLayout {

	private CheckBox cb;
	private BeanItem<MovieProductEntry> productItem;

	public MovieEntryDetailView(BeanItem<MovieProductEntry> productItem, boolean checkbox) {
		setProductItem(productItem);
		Property cover = productItem.getItemProperty("cover");
		Property ean = productItem.getItemProperty("ean");
		if (cover != null && cover.getValue() != null) {
			Embedded em = new Embedded("", new ExternalResource((String) cover.getValue()));
			em.setWidth("100px");
			em.setStyleName("centered");
			addComponent(em);

		}
		Form mpeForm = new Form();
		mpeForm.setData(productItem);
		mpeForm.setItemDataSource(productItem, Arrays.asList(new MovieProductEntry().form_fields()));
		mpeForm.setImmediate(true);
		mpeForm.setReadOnly(true);
		addComponent(mpeForm);
		cb = new CheckBox((String) ean.getValue());
		cb.setStyleName("centered");
		cb.setVisible(checkbox);
		addComponent(cb);
	}

	public MovieEntryDetailView(BeanItem<MovieProductEntry> productItem) {
		this(productItem, false);
	}

	public boolean isChecked() {
		return cb.booleanValue();
	}

	public BeanItem<MovieProductEntry> getProductItem() {
		return productItem;
	}

	public void setProductItem(BeanItem<MovieProductEntry> productItem) {
		this.productItem = productItem;
	}

}
