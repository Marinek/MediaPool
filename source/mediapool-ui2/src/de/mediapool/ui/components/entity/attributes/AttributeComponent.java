package de.mediapool.ui.components.entity.attributes;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import de.mediapool.core.beans.business.entity.attributes.AttributeValueBean;

public class AttributeComponent extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout myLayout;

	@AutoGenerated
	private Label attributeLabel;

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public AttributeComponent(AttributeValueBean<?> attribute) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.addViewComponentFor(attribute);

		this.attributeLabel.setValue(attribute.getAttributeDisplayName());

		this.setWidth(100, Unit.PERCENTAGE);
		this.setHeight(36, Unit.PIXELS);
	}

	@SuppressWarnings("unchecked")
	private void addViewComponentFor(AttributeValueBean<?> attribute) {
		try {
			Class<AbstractField<Object>> lClass = (Class<AbstractField<Object>>) Class.forName(attribute.getAttributeType().getViewClassName());

			AbstractField<Object> lNewComponent = lClass.newInstance();

			lNewComponent.setValue(attribute.getAttributeValue());

			lNewComponent.setWidth(100, Unit.PERCENTAGE);

			myLayout.addComponent(lNewComponent);

		} catch (Throwable e) {
			e.printStackTrace();
			myLayout.addComponent(new Label("Attribut konnte nicht geladen werden."));
		}
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// myLayout
		myLayout = buildMyLayout();
		mainLayout.addComponent(myLayout, "top:0.0px;right:0.0px;left:0.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildMyLayout() {
		// common part: create layout
		myLayout = new HorizontalLayout();
		myLayout.setImmediate(false);
		myLayout.setWidth("100.0%");
		myLayout.setHeight("-1px");
		myLayout.setMargin(true);

		// attributeLabel
		attributeLabel = new Label();
		attributeLabel.setImmediate(false);
		attributeLabel.setWidth("200px");
		attributeLabel.setHeight("-1px");
		attributeLabel.setValue("Label");
		myLayout.addComponent(attributeLabel);
		myLayout.setComponentAlignment(attributeLabel, new Alignment(33));

		return myLayout;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
