package de.mediapool.web.ui.widgets;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.MenuBar;

@SuppressWarnings("serial")
public class MediaMenuBar extends MenuBar {

	public MediaMenuBar() {

		// Save reference to individual items so we can add sub-menu items to
		// them
		final MenuBar.MenuItem file = addItem("File", null);
		final MenuBar.MenuItem newItem = file.addItem("New", null);
		file.addItem("Open file...", menuCommand);
		file.addSeparator();

		newItem.addItem("File", menuCommand);
		newItem.addItem("Folder", menuCommand);
		newItem.addItem("Project...", menuCommand);

		file.addItem("Close", menuCommand);
		file.addItem("Close All", menuCommand);
		file.addSeparator();

		file.addItem("Save", menuCommand);
		file.addItem("Save As...", menuCommand);
		file.addItem("Save All", menuCommand);

		final MenuBar.MenuItem edit = addItem("Edit", null);
		edit.addItem("Undo", menuCommand);
		edit.addItem("Redo", menuCommand).setEnabled(false);
		edit.addSeparator();

		edit.addItem("Cut", menuCommand);
		edit.addItem("Copy", menuCommand);
		edit.addItem("Paste", menuCommand);
		edit.addSeparator();

		final MenuBar.MenuItem find = edit.addItem("Find/Replace", menuCommand);

		// Actions can be added inline as well, of course
		find.addItem("Google Search", new Command() {
			public void menuSelected(MenuItem selectedItem) {
				getWindow().open(new ExternalResource("http://www.google.com"));
			}
		});
		find.addSeparator();
		find.addItem("Find/Replace...", menuCommand);
		find.addItem("Find Next", menuCommand);
		find.addItem("Find Previous", menuCommand);

		final MenuBar.MenuItem view = addItem("View", null);
		view.addItem("Show/Hide Status Bar", menuCommand);
		view.addItem("Customize Toolbar...", menuCommand);
		view.addSeparator();

		view.addItem("Actual Size", menuCommand);
		view.addItem("Zoom In", menuCommand);
		view.addItem("Zoom Out", menuCommand);

	}

	private Command menuCommand = new Command() {
		public void menuSelected(MenuItem selectedItem) {
			getWindow().showNotification("Action " + selectedItem.getText());
		}
	};

}