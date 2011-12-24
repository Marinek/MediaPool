package de.mediapool.web.client.gui;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SingleSelectionModel;

import de.mediapool.web.client.dto.Movie;

public class MovieListTable extends CellTable<Movie> {

	List<Movie> movieList;

	public List<Movie> getMovieList() {
		return getMfw().getMovieList();
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public MovieListTable(List<Movie> movieList) {
		this.setStyleName("list_view_table");
		this.setSelectionModel(new SingleSelectionModel<Movie>());

		this.addCellPreviewHandler(new CellPreviewEvent.Handler<Movie>() {
			public void onCellPreview(CellPreviewEvent<Movie> event) {
				String type = event.getNativeEvent().getType();
				if (type.equals("click")) {
					getMfw().setSelectedMovie(event.getValue());
					((DataForm) getMfw().getWidget("DataForm")).refreshForm();
					((ImageForm) getMfw().getWidget("ImageForm")).refreshForm();
				}
			}
		});

		buildAllColumns();
		fillMovieTable();
	}

	private void buildAllColumns() {

		TextColumn<Movie> titleColumn = new TextColumn<Movie>() {
			@Override
			public String getValue(Movie movie) {
				return movie.getTitle();
			}
		};
		TextColumn<Movie> genreColumn = new TextColumn<Movie>() {
			@Override
			public String getValue(Movie movie) {
				return movie.getGenre();
			}
		};
		TextColumn<Movie> idColumn = new TextColumn<Movie>() {
			@Override
			public String getValue(Movie movie) {
				return movie.getId() + "";
			}
		};

		titleColumn.setSortable(true);
		this.addColumn(idColumn, "ID");
		this.addColumn(titleColumn, "Titel");
		this.addColumn(genreColumn, "Genre");
	}

	private void addSortable(List<Movie> list) {
		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Movie> columnSortHandler = new ListHandler<Movie>(list);
		columnSortHandler.setComparator(this.getColumn(1), new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getTitle().compareTo(o2.getTitle()) : 1;
				}
				return -1;
			}
		});
		this.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		this.getColumnSortList().push(this.getColumn(1));
	}

	public void fillMovieTable() {
		// Create a data provider.
		ListDataProvider<Movie> dataProvider = new ListDataProvider<Movie>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(this);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		List<Movie> list = dataProvider.getList();
		if (getMovieList() != null) {

			for (Movie movie : getMovieList()) {
				list.add(movie);
			}
		} else {
			Movie dummy = new Movie();
			dummy.setTitle("Es wurden keine Filme gefunden");
			list.add(dummy);
		}
		addSortable(list);
		int max = getMovieList() != null ? getMovieList().size() : 0;
		Range range = new Range(0, max);
		this.setVisibleRange(range);

		this.redraw();
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}
}
