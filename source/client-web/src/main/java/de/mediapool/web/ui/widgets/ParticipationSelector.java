package de.mediapool.web.ui.widgets;

import org.vaadin.addon.customfield.CustomField;

/**
 * A custom field that allows selection of a department.
 */
public class ParticipationSelector extends CustomField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Class<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	// private ComboBox partBox = new ComboBox();
	// // private ComboBox memberBox = new ComboBox();
	//
	// private JPAContainer<Participation> participations;
	//
	// @SuppressWarnings("serial")
	// public ParticipationSelector() {
	// // participations = MediaService.getAllParticipation();
	// // members = MediaService.getAllPMember();
	//
	// setCaption("Participations");
	//
	// partBox.setContainerDataSource(participations);
	// partBox.setItemCaptionPropertyId("part");
	// partBox.setImmediate(true);
	//
	// // container.setApplyFiltersImmediately(false);
	//
	// // filterDepartments(null);
	// // memberBox.setContainerDataSource(members);
	// // memberBox.setItemCaptionPropertyId("person");
	//
	// partBox.addListener(new Property.ValueChangeListener() {
	// @Override
	// public void valueChange(com.vaadin.data.Property.ValueChangeEvent event)
	// {
	// /*
	// * Modify filtering of the department combobox
	// */
	// // EntityItem<Participation> item =
	// // participations.getItem(partBox.getValue());
	// // Participation entity = item.getEntity();
	// // filterDepartments(entity);
	// }
	// });
	// // memberBox.addListener(new Property.ValueChangeListener() {
	// // @Override
	// // public void valueChange(com.vaadin.data.Property.ValueChangeEvent
	// // event) {
	// // /*
	// // * Modify the actual value of the custom field.
	// // */
	// // if (memberBox.getValue() == null) {
	// // setValue(null, false);
	// // } else {
	// // PMember entity = members.getItem(memberBox.getValue()).getEntity();
	// // setValue(entity, false);
	// // }
	// // }
	// // });
	//
	// CssLayout cssLayout = new CssLayout();
	// cssLayout.addComponent(partBox);
	// // cssLayout.addComponent(memberBox);
	// setCompositionRoot(cssLayout);
	// }
	//
	// /**
	// * Modify available options based on the "geo deparment" select.
	// *
	// * @param currentGeoDepartment
	// */
	// // private void filterDepartments(Participation participation) {
	// // if (participation == null) {
	// // // memberBox.setValue(null);
	// // // memberBox.setEnabled(false);
	// // } else {
	// // participations.removeAllContainerFilters();
	// // // participations.addContainerFilter(new Equal("parent",
	// // // participation));
	// // participations.applyFilters();
	// // // memberBox.setValue(null);
	// // // memberBox.setEnabled(true);
	// // }
	// // }
	//
	// @Override
	// public void setPropertyDataSource(Property newDataSource) {
	// super.setPropertyDataSource(newDataSource);
	// setParticipation(newDataSource.getValue());
	// }
	//
	// @Override
	// public void setValue(Object newValue) throws ReadOnlyException,
	// ConversionException {
	// setParticipation(newValue);
	// }
	//
	// private void setParticipation(Object newValue) {
	// Participation value = (Participation) newValue;
	// partBox.setValue(value != null ? value.getId() : null);
	// // memberBox.setValue(value != null ? value.getId() : null);
	// }
	//
	// @Override
	// public Class<?> getType() {
	// return Participation.class;
	// }

}
