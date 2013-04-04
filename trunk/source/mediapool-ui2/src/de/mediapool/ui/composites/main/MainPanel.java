package de.mediapool.ui.composites.main;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.ui.composites.main.sub.media.MediaTableSub;
import de.mediapool.ui.composites.navigation.components.MediaNavigationTree;
import de.mediapool.ui.ressource.MPImages;

public class MainPanel extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private VerticalLayout verticalLayout_1;

	@AutoGenerated
	private HorizontalSplitPanel mainSplitPanel;

	@AutoGenerated
	private Accordion menueAccordion;

	@AutoGenerated
	private Embedded titleImg;

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public MainPanel() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		Label l2 = new Label("There are no saved notes.");
		Label l3 = new Label("There are currently no issues.");

		menueAccordion.addTab(new MediaNavigationTree(), "Media Kategorien", null);

		menueAccordion.addTab(l2, "Notes", null);
		menueAccordion.addTab(l3, "Issues", null);

		menueAccordion.setSizeFull();

		this.mainSplitPanel.setSplitPosition(250, Unit.PIXELS);

	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public void enter(ViewChangeEvent event) {
		Notification.show(event.getParameters());

		// TODO: Hier kommt noch was hin, dass die Parameter ausweret und
		// entsprechende Components im rechten Bereich anzeigt.

		this.mainSplitPanel.setSecondComponent(new MediaTableSub().getComponent());
		this.verticalLayout_1.setSizeFull();
		this.verticalLayout_1.setExpandRatio(this.titleImg, 2);
		this.verticalLayout_1.setExpandRatio(this.mainSplitPanel, 16);

		this.titleImg.setSource(MPImages.APPLICATION_TITLE_RES);
		this.titleImg.setHeight("112px");
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setStyleName("root");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		mainLayout.addComponent(verticalLayout_1, "top:0.0px;right:0.0px;bottom:0.0px;left:0.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);

		// titleImg
		titleImg = new Embedded();
		titleImg.setImmediate(false);
		titleImg.setWidth("-1px");
		titleImg.setHeight("-1px");
		titleImg.setSource(new ThemeResource("img/component/embedded_icon.png"));
		titleImg.setType(1);
		titleImg.setMimeType("image/png");
		verticalLayout_1.addComponent(titleImg);
		verticalLayout_1.setComponentAlignment(titleImg, new Alignment(33));

		// mainSplitPanel
		mainSplitPanel = buildMainSplitPanel();
		verticalLayout_1.addComponent(mainSplitPanel);

		return verticalLayout_1;
	}

	@AutoGenerated
	private HorizontalSplitPanel buildMainSplitPanel() {
		// common part: create layout
		mainSplitPanel = new HorizontalSplitPanel();
		mainSplitPanel.setImmediate(false);
		mainSplitPanel.setWidth("100.0%");
		mainSplitPanel.setHeight("100.0%");

		// menueAccordion
		menueAccordion = new Accordion();
		menueAccordion.setImmediate(false);
		menueAccordion.setWidth("-1px");
		menueAccordion.setHeight("-1px");
		mainSplitPanel.addComponent(menueAccordion);

		return mainSplitPanel;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
