package de.mediapool.web.ui.elements;

import java.util.Iterator;

import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.client.ui.dd.VerticalDropLocation;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.Tree.TreeTargetDetails;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MediaTree extends VerticalLayout {

	public MediaTree() {
		setSpacing(true);

		Tree tree = new Tree("Tree sortable using drag'n'drop");

		// Populate the tree
		HierarchicalContainer container = getMediaContainer();
		tree.setContainerDataSource(container);
		tree.setItemCaptionPropertyId(hw_PROPERTY_NAME);
		tree.setItemIconPropertyId(hw_PROPERTY_ICON);

		// Allow all nodes to have children
		for (Object itemId : tree.getItemIds()) {
			tree.setChildrenAllowed(itemId, true);
		}

		// Expand all nodes
		for (Iterator<?> it = tree.rootItemIds().iterator(); it.hasNext();) {
			tree.expandItemsRecursively(it.next());
		}
		tree.setDragMode(TreeDragMode.NODE);
		tree.setDropHandler(new TreeSortDropHandler(tree, container));

		addComponent(tree);
	}

	private static class TreeSortDropHandler implements DropHandler {
		private final Tree tree;

		/**
		 * Tree must use {@link HierarchicalContainer}.
		 * 
		 * @param tree
		 */
		public TreeSortDropHandler(Tree tree, HierarchicalContainer container) {
			this.tree = tree;
		}

		public AcceptCriterion getAcceptCriterion() {
			// Alternatively, could use the following criteria to eliminate some
			// checks in drop():
			// new And(IsDataBound.get(), new DragSourceIs(tree));
			return AcceptAll.get();
		}

		public void drop(DragAndDropEvent dropEvent) {
			// Called whenever a drop occurs on the component

			// Make sure the drag source is the same tree
			Transferable t = dropEvent.getTransferable();

			// see the comment in getAcceptCriterion()
			if (t.getSourceComponent() != tree || !(t instanceof DataBoundTransferable)) {
				return;
			}

			TreeTargetDetails dropData = ((TreeTargetDetails) dropEvent.getTargetDetails());

			Object sourceItemId = ((DataBoundTransferable) t).getItemId();
			// FIXME: Why "over", should be "targetItemId" or just
			// "getItemId"
			Object targetItemId = dropData.getItemIdOver();

			// Location describes on which part of the node the drop took
			// place
			VerticalDropLocation location = dropData.getDropLocation();

			moveNode(sourceItemId, targetItemId, location);

		}

		/**
		 * Move a node within a tree onto, above or below another node depending
		 * on the drop location.
		 * 
		 * @param sourceItemId
		 *            id of the item to move
		 * @param targetItemId
		 *            id of the item onto which the source node should be moved
		 * @param location
		 *            VerticalDropLocation indicating where the source node was
		 *            dropped relative to the target node
		 */
		private void moveNode(Object sourceItemId, Object targetItemId, VerticalDropLocation location) {
			HierarchicalContainer container = (HierarchicalContainer) tree.getContainerDataSource();

			// Sorting goes as
			// - If dropped ON a node, we append it as a child
			// - If dropped on the TOP part of a node, we move/add it before
			// the node
			// - If dropped on the BOTTOM part of a node, we move/add it
			// after the node

			if (location == VerticalDropLocation.MIDDLE) {
				if (container.setParent(sourceItemId, targetItemId) && container.hasChildren(targetItemId)) {
					// move first in the container
					container.moveAfterSibling(sourceItemId, null);
				}
			} else if (location == VerticalDropLocation.TOP) {
				Object parentId = container.getParent(targetItemId);
				if (container.setParent(sourceItemId, parentId)) {
					// reorder only the two items, moving source above target
					container.moveAfterSibling(sourceItemId, targetItemId);
					container.moveAfterSibling(targetItemId, sourceItemId);
				}
			} else if (location == VerticalDropLocation.BOTTOM) {
				Object parentId = container.getParent(targetItemId);
				if (container.setParent(sourceItemId, parentId)) {
					container.moveAfterSibling(sourceItemId, targetItemId);
				}
			}
		}
	}

	public static HierarchicalContainer getMediaContainer() {
		Item item = null;
		int itemId = 0; // Increasing numbering for itemId:s

		// Create new container
		HierarchicalContainer hwContainer = new HierarchicalContainer();
		// Create containerproperty for name
		hwContainer.addContainerProperty(hw_PROPERTY_NAME, String.class, null);
		// Create containerproperty for icon
		hwContainer.addContainerProperty(hw_PROPERTY_ICON, ThemeResource.class, new ThemeResource(
				"../runo/icons/16/document.png"));
		for (int i = 0; i < media.length; i++) {
			// Add new item
			item = hwContainer.addItem(itemId);
			// Add name property for item
			item.getItemProperty(hw_PROPERTY_NAME).setValue(media[i][0]);
			// Allow children
			hwContainer.setChildrenAllowed(itemId, true);
			itemId++;
			for (int j = 1; j < media[i].length; j++) {
				if (j == 1) {
					item.getItemProperty(hw_PROPERTY_ICON).setValue(new ThemeResource("../runo/icons/16/folder.png"));
				}
				// Add child items
				item = hwContainer.addItem(itemId);
				item.getItemProperty(hw_PROPERTY_NAME).setValue(media[i][j]);
				hwContainer.setParent(itemId, itemId - j);
				hwContainer.setChildrenAllowed(itemId, false);

				itemId++;
			}
		}
		return hwContainer;
	}

	public static final Object hw_PROPERTY_NAME = "name";
	public static final Object hw_PROPERTY_ICON = "icon";

	private static final String[][] media = { { "Filme", "Lieblinge", "Meine", "Ungelesen" },
			{ "Spiele", "Lieblinge", "Meine", "Ungelesen" }, { "Bücher", "Lieblinge", "Meine", "Ungelesen" } };

}