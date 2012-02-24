package de.mediapool.web.ui.impl;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Used to confirm events.
 * 
 * @author Joonas Lehtinen, Tommi Laukkanen
 */
public final class ConfirmationDialog extends Window implements Button.ClickListener {
	/** Confirmation dialog width. */
	private static final int CONFIRMATION_DIALOG_HEIGHT = 200;
	/** Confirmation dialog height. */
	private static final int CONFIRMATION_DIALOG_WIDTH = 320;
	/** Constant for 100 percent value used in dialog main layout width. */
	private static final int ONE_HUNDRED_PERCENT = 100;
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	/** The confirmation callback. */
	private final ConfirmationDialogCallback callback;
	/** Yes button. */
	private final Button okButton;
	/** No button. */
	private final Button cancelButton;

	/**
	 * Constructor for configuring confirmation dialog.
	 * 
	 * @param caption
	 *            the dialog caption.
	 * @param question
	 *            the question.
	 * @param okLabel
	 *            the Ok button label.
	 * @param cancelLabel
	 *            the cancel button label.
	 * @param callback
	 *            the callback.
	 */
	public ConfirmationDialog(final String caption, final String question, final String okLabel,
			final String cancelLabel, final ConfirmationDialogCallback callback) {
		super(caption);
		setWidth(CONFIRMATION_DIALOG_WIDTH, ConfirmationDialog.UNITS_PIXELS);
		setHeight(CONFIRMATION_DIALOG_HEIGHT, ConfirmationDialog.UNITS_PIXELS);
		okButton = new Button(okLabel, this);
		cancelButton = new Button(cancelLabel, this);
		setModal(true);

		this.callback = callback;

		if (question != null) {
			addComponent(new Label(question));
		}

		final HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.addComponent(okButton);
		buttonLayout.addComponent(cancelButton);
		addComponent(buttonLayout);
		((VerticalLayout) getContent()).setHeight(ONE_HUNDRED_PERCENT, ConfirmationDialog.UNITS_PERCENTAGE);
		((VerticalLayout) getContent()).setComponentAlignment(buttonLayout, Alignment.BOTTOM_CENTER);
	}

	/**
	 * Event handler for button clicks.
	 * 
	 * @param event
	 *            the click event.
	 */
	public void buttonClick(final ClickEvent event) {
		if (getParent() != null) {
			((Window) getParent()).removeWindow(this);
		}
		callback.response(event.getSource() == okButton);
	}

	/**
	 * Interface for confirmation dialog callbacks.
	 */
	public interface ConfirmationDialogCallback {
		/**
		 * The user response.
		 * 
		 * @param ok
		 *            True if user clicked ok.
		 */
		void response(boolean ok);
	}

}