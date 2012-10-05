package de.mediapool.web.ui.widgets;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

/**
 * A Table Implementation that supports columnShowChanged, columnOrderChanged
 * and columnSortChanges events.
 * 
 */
public class TableImpl extends Table {

	protected static Method LISTENER_METHOD_COLUMN_ORDER_CHANGED;

	protected static Method LISTENER_METHOD_COLUMN_SHOWN_CHANGED;

	protected static Method LISTENER_METHOD_COLUMN_SORT_CHANGED;

	private static final long serialVersionUID = 1L;
	static {
		try {
			LISTENER_METHOD_COLUMN_SHOWN_CHANGED = ColumnShownChangedListener.class.getDeclaredMethod(
					"columnShownChanged", new Class[] { ColumnShownChangedEvent.class });
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		try {
			LISTENER_METHOD_COLUMN_ORDER_CHANGED = ColumnOrderChangedListener.class.getDeclaredMethod(
					"columnOrderChanged", new Class[] { ColumnOrderChangedEvent.class });
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		try {
			LISTENER_METHOD_COLUMN_SORT_CHANGED = ColumnSortChangedListener.class.getDeclaredMethod(
					"columnSortChanged", new Class[] { ColumnSortChangedEvent.class });
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public TableImpl() {
		super();
		addStyleName("sc-table");
	}

	public TableImpl(String caption) {
		super(caption);
		addStyleName("sc-table");
	}

	public TableImpl(String caption, Container dataSource) {
		super(caption, dataSource);
		addStyleName("sc-table");
	}

	public void addListener(ColumnOrderChangedListener listener) {
		addListener(TableImpl.ColumnOrderChangedEvent.class, listener, LISTENER_METHOD_COLUMN_ORDER_CHANGED);
	}

	public void addListener(ColumnShownChangedListener listener) {
		addListener(TableImpl.ColumnShownChangedEvent.class, listener, LISTENER_METHOD_COLUMN_SHOWN_CHANGED);
	}

	public void addListener(ColumnSortChangedListener listener) {
		addListener(TableImpl.ColumnSortChangedEvent.class, listener, LISTENER_METHOD_COLUMN_SORT_CHANGED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.Table#changeVariables(java.lang.Object, java.util.Map)
	 */
	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {
		super.changeVariables(source, variables);

		if (variables.containsKey("collapsedcolumns")) {
			fireColumnShowChanged();
		}

		if (variables.containsKey("columnorder")) {
			fireColumnOrderChanged();
		}

		if (variables.containsKey("sortcolumn") || variables.containsKey("sortascending")) {
			fireColumnSortChanged();
		}

	}

	protected void fireColumnOrderChanged() {
		fireEvent(new ColumnOrderChangedEvent(this));
	}

	protected void fireColumnShowChanged() {
		fireEvent(new ColumnShownChangedEvent(this));
	}

	protected void fireColumnSortChanged() {
		fireEvent(new ColumnSortChangedEvent(this));
	}

	public static final class ColumnOrderChangedEvent extends Event {

		private static final long serialVersionUID = 1L;

		public ColumnOrderChangedEvent(TableImpl source) {
			super(source);
		}
	}

	public interface ColumnOrderChangedListener extends Serializable {

		void columnOrderChanged(ColumnOrderChangedEvent event);
	}

	public static final class ColumnShownChangedEvent extends Event {

		private static final long serialVersionUID = 1L;

		public ColumnShownChangedEvent(TableImpl source) {
			super(source);
		}
	}

	public interface ColumnShownChangedListener extends Serializable {

		void columnShownChanged(ColumnShownChangedEvent event);
	}

	public static final class ColumnSortChangedEvent extends Event {

		private static final long serialVersionUID = 1L;

		public ColumnSortChangedEvent(TableImpl source) {
			super(source);
		}
	}

	public interface ColumnSortChangedListener extends Serializable {

		void columnSortChanged(ColumnSortChangedEvent event);
	}

}