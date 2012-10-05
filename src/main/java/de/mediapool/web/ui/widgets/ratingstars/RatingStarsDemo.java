package de.mediapool.web.ui.widgets.ratingstars;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

/**
 * A demo application for the RatingStars component. For a live demo see
 * {@link http://teemu.virtuallypreinstalled.com/RatingStars}.
 * 
 * @author Teemu Pöntelin / IT Mill Ltd
 */
public class RatingStarsDemo extends Application {

	private static final long serialVersionUID = 878415417860536617L;

	private Table table;

	private Set<RatingStars> allRatingStars = new HashSet<RatingStars>();

	private CheckBox animatedCheckBox;

	private Window mainWindow;

	private Panel mainPanel;

	private String[] movieNames = { "The Matrix", "Memento", "Kill Bill: Vol. 1" };

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(5);

	static {
		valueCaptions.put(1, "Epic Fail");
		valueCaptions.put(2, "Poor");
		valueCaptions.put(3, "OK");
		valueCaptions.put(4, "Good");
		valueCaptions.put(5, "Excellent");
	}

	@Override
	public void init() {
		initWindowAndDescription();
		initDemoPanel();
	}

	private void initWindowAndDescription() {
		mainWindow = new Window("RatingStars Component Demo");
		setMainWindow(mainWindow);

		VerticalLayout centerLayout = new VerticalLayout();
		centerLayout.setMargin(true);

		mainPanel = new Panel();
		mainPanel.setWidth("750px");
		centerLayout.addComponent(mainPanel);
		centerLayout.setComponentAlignment(mainPanel, Alignment.TOP_CENTER);
		mainWindow.setContent(centerLayout);

		StringBuilder descriptionXhtml = new StringBuilder();
		descriptionXhtml.append("<h1>RatingStars Component Demo</h1>");
		descriptionXhtml.append("<p>RatingStars is a simple component for giving rating values.</p>");
		descriptionXhtml
				.append("<p>Download and rate this component at <a href=\"http://vaadin.com/directory#addon/20\">Vaadin Directory</a>.</p>");
		descriptionXhtml.append("<p>Highlights:</p>");
		descriptionXhtml.append("<ul>");
		descriptionXhtml
				.append("<li>Keyboard usage (focus with tab, navigate with arrow keys, select with enter)</li>");
		descriptionXhtml.append("<li>Easily customizable appearance</li>");
		descriptionXhtml.append("<li>Captions for individual values</li>");
		descriptionXhtml.append("<li>Optional transition animations</li>");
		descriptionXhtml.append("</ul>");

		Label description = new Label(descriptionXhtml.toString(), Label.CONTENT_XHTML);
		mainPanel.addComponent(description);
	}

	private void initDemoPanel() {
		Panel demoPanel = new Panel("Demonstration");
		VerticalLayout demoLayout = new VerticalLayout();
		demoLayout.setSpacing(true);
		demoLayout.setMargin(true);
		demoPanel.setContent(demoLayout);
		mainPanel.addComponent(demoPanel);

		// animated checkbox
		animatedCheckBox = new CheckBox("Animated?");
		animatedCheckBox.setValue(true);
		animatedCheckBox.setImmediate(true);
		animatedCheckBox.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = -1291394320556343373L;

			public void buttonClick(ClickEvent event) {
				for (RatingStars rs : allRatingStars) {
					rs.setAnimated((Boolean) event.getButton().getValue());
				}
			}
		});
		demoLayout.addComponent(animatedCheckBox);

		// create and populate the movie table
		table = new Table("Rate your favourite movies");
		table.addContainerProperty("Movie", String.class, null);
		table.addContainerProperty("Comment", TextField.class, null);
		table.addContainerProperty("Your rating", RatingStars.class, null);
		table.addContainerProperty("Average rating", RatingStars.class, null);
		populateTable();
		table.setPageLength(table.getItemIds().size());
		demoLayout.addComponent(table);

		// theme demos
		demoLayout.addComponent(new Label("<div style=\"margin-top: 20px\">"
				+ "The component has two built-in styles.</div>", Label.CONTENT_XHTML));
		RatingStars defaultRs = new RatingStars();
		defaultRs.setCaption("default");
		allRatingStars.add(defaultRs);

		RatingStars tinyRs = new RatingStars();
		tinyRs.setStyleName("tiny");
		tinyRs.setCaption("tiny");
		allRatingStars.add(tinyRs);

		HorizontalLayout themeLayout = new HorizontalLayout();
		themeLayout.setSpacing(true);
		themeLayout.addComponent(defaultRs);
		themeLayout.addComponent(tinyRs);
		demoLayout.addComponent(themeLayout);

		// component states
		demoLayout.addComponent(new Label("<div style=\"margin-top: 20px\">" + "Component states</div>",
				Label.CONTENT_XHTML));
		RatingStars disabledRs = new RatingStars();
		disabledRs.setCaption("disabled");
		disabledRs.setValue(2.5);
		disabledRs.setEnabled(false);

		RatingStars readonlyRs = new RatingStars();
		readonlyRs.setCaption("read-only");
		readonlyRs.setValue(2.5);
		readonlyRs.setReadOnly(true);

		HorizontalLayout stateLayout = new HorizontalLayout();
		stateLayout.setSpacing(true);
		stateLayout.addComponent(disabledRs);
		stateLayout.addComponent(readonlyRs);
		demoLayout.addComponent(stateLayout);
	}

	/**
	 * Populate the table with some random data.
	 */
	private void populateTable() {
		Random r = new Random();
		for (final String movieName : movieNames) {
			final TextField textField = new TextField();

			final RatingStars avgRs = new RatingStars();
			avgRs.setMaxValue(5);
			avgRs.setValue(r.nextFloat() * 4 + 1);
			avgRs.setReadOnly(true);
			allRatingStars.add(avgRs);

			final RatingStars yourRs = new RatingStars();
			yourRs.setMaxValue(5);
			yourRs.setImmediate(true);
			yourRs.setDescription("Your rating");
			yourRs.setValueCaption(valueCaptions.values().toArray(new String[5]));
			yourRs.addListener(new Property.ValueChangeListener() {

				private static final long serialVersionUID = -3277119031169194273L;

				public void valueChange(ValueChangeEvent event) {
					Double value = (Double) event.getProperty().getValue();

					RatingStarsDemo.this.getMainWindow()
							.showNotification("You voted " + value + " stars for " + movieName + ".",
									Notification.TYPE_TRAY_NOTIFICATION);

					RatingStars changedRs = (RatingStars) event.getProperty();
					// reset value captions
					changedRs.setValueCaption(valueCaptions.values().toArray(new String[5]));
					// set "Your Rating" caption
					changedRs.setValueCaption((int) Math.round(value), "Your Rating");

					// dummy logic to calculate "average" value
					avgRs.setReadOnly(false);
					avgRs.setValue((((Double) avgRs.getValue()) + value) / 2);
					avgRs.setReadOnly(true);
				}
			});
			allRatingStars.add(yourRs);

			Object itemId = table.addItem();
			Item i = table.getItem(itemId);
			i.getItemProperty("Movie").setValue(movieName);
			i.getItemProperty("Comment").setValue(textField);
			i.getItemProperty("Your rating").setValue(yourRs);
			i.getItemProperty("Average rating").setValue(avgRs);
		}
	}
}
