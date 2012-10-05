package de.mediapool.web.ui.widgets;

import java.lang.reflect.Method;
import java.util.Map;

import com.vaadin.terminal.gwt.client.ui.VSplitPanel;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * A Custom split-panel with a little bit more flexibility regarding events of
 * resize.
 * 
 */
public class SplitPanelImpl extends VerticalSplitPanel {

	public static Method LISTENER_METHOD_SPLITTER_POSITION_CHANGED;

	private static final long serialVersionUID = 1L;

	static {
		try {
			LISTENER_METHOD_SPLITTER_POSITION_CHANGED = SplitterPositionChangedListener.class.getDeclaredMethod(
					"splitterPositionChanged", new Class[] { SplitterPositionChangedEvent.class });
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public SplitPanelImpl() {
		super();
		addStyleName("sc-split-panel");
	}

	public void addListener(SplitterPositionChangedListener listener) {
		addListener(SplitPanelImpl.SplitterPositionChangedEvent.class, listener,
				LISTENER_METHOD_SPLITTER_POSITION_CHANGED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.SplitPanel#changeVariables(java.lang.Object,
	 * java.util.Map)
	 */
	@Override
	public void changeVariables(Object source, Map variables) {
		boolean notify = false;
		if (variables.containsKey("position") && !isLocked()
				&& !variables.containsKey(VSplitPanel.SPLITTER_CLICK_EVENT_IDENTIFIER)) {
			Integer newPos = (Integer) variables.get("position");
			if (newPos < getSplitPosition() - 1 || newPos > getSplitPosition() + 1) {
				notify = true;
			}
		}
		super.changeVariables(source, variables);
		if (notify) {
			fireEvent(new SplitterPositionChangedEvent(this));
		}

	}

	protected void fireSplitterPositionChanged() {
		fireEvent(new SplitterPositionChangedEvent(this));
	}

	public void setSplitPosition(int pos, int unit, boolean notify) {
		super.setSplitPosition(pos, unit);
		if (notify) {
			fireEvent(new SplitterPositionChangedEvent(this));
		}
	}

	public static final class SplitterPositionChangedEvent extends Event {

		private static final long serialVersionUID = 1L;

		public SplitterPositionChangedEvent(SplitPanelImpl source) {
			super(source);
		}
	}

	public interface SplitterPositionChangedListener {

		void splitterPositionChanged(SplitterPositionChangedEvent event);
	}

}