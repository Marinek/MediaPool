package com.vaadin.demo.jpaaddressbook;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.demo.jpaaddressbook.domain.Department;

public class HierarchicalDepartmentContainer extends JPAContainer<Department> {

	public HierarchicalDepartmentContainer() {
		super(Department.class);
		// setEntityProvider(new CachingLocalEntityProvider<Department>(
		// Department.class,
		// JPAContainerFactory
		// .createEntityManagerForPersistenceUnit(MediapoolApplication.PERSISTENCE_UNIT)));
		// setParentProperty("parent");
	}

	@Override
	public boolean areChildrenAllowed(Object itemId) {
		return super.areChildrenAllowed(itemId) && getItem(itemId).getEntity().isSuperDepartment();
	}

}
