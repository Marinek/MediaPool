package de.mediapool.web.client;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.mediapool.web.client.dto.Movie;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Media implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final MediaServiceAsync mediaService = GWT
			.create(MediaService.class);

	List<Movie> movieList;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		mediaService.getAllMovies(new AsyncCallback<List<Movie>>() {
			public void onFailure(Throwable caught) {
				setMovieList(null);
				System.out.println(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(List<Movie> list) {
				setMovieList(list);
				System.out.println("ALLES OK" + list.get(0).getTitle());
			}
		});

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("1024", "768");

		DockPanel dockPanel = new DockPanel();
		rootPanel.add(dockPanel);
		final Label errorLabel = new Label();
		dockPanel.add(errorLabel, DockPanel.WEST);
		final Button sendButton = new Button("Send");
		dockPanel.add(sendButton, DockPanel.WEST);
		sendButton.setText("Suche");

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		final TextBox nameField = new TextBox();
		dockPanel.add(nameField, DockPanel.WEST);
		nameField.setSize("149px", "24px");

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		Image image = new Image("test");
		rootPanel.add(image, 214, 89);
		image.setSize("168px", "140px");

		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 214, 235);
		verticalPanel.setSize("168px", "26px");

		Label lblNewLabel = new Label("New label");
		verticalPanel.add(lblNewLabel);

		Label lblNewLabel_1 = new Label("New label");
		verticalPanel.add(lblNewLabel_1);
		lblNewLabel_1.setSize("167px", "16px");

		CellTable<Movie> cellTable = new CellTable<Movie>();
		rootPanel.add(cellTable, 10, 85);
		cellTable.setSize("198px", "317px");

		// Create name column.
		TextColumn<Movie> titleColumn = new TextColumn<Movie>() {
			@Override
			public String getValue(Movie movie) {
				return movie.getTitle();
			}
		};

		// Make the name column sortable.
		titleColumn.setSortable(true);

		cellTable.addColumn(titleColumn, "Titel");

		// Create a data provider.
		ListDataProvider<Movie> dataProvider = new ListDataProvider<Movie>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(cellTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		List<Movie> list = dataProvider.getList();
		if (getMovieList() != null) {

			for (Movie movie : getMovieList()) {
				list.add(movie);
			}
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Movie> columnSortHandler = new ListHandler<Movie>(list);
		columnSortHandler.setComparator(titleColumn, new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getTitle()
							.compareTo(o2.getTitle()) : 1;
				}
				return -1;
			}
		});
		cellTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		cellTable.getColumnSortList().push(titleColumn);

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				mediaService.getAllMovies(new AsyncCallback<List<Movie>>() {
					public void onFailure(Throwable caught) {
						setMovieList(null);
					}

					@Override
					public void onSuccess(List<Movie> list) {
						setMovieList(list);
					}
				});

			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		nameField.addKeyUpHandler(handler);
		sendButton.addClickHandler(handler);
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

}
