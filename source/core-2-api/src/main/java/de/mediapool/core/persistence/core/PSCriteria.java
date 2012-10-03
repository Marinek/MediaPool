package de.mediapool.core.persistence.core;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

public class PSCriteria extends DetachedCriteria {

	protected PSCriteria(String entityName) {
		super(entityName);
	}

	private static final long serialVersionUID = 1L;

	public DetachedCriteria add(Criterion criterion) {

		return super.add(criterion);

	}

	public DetachedCriteria addOrder(Order order) {
		return super.addOrder(order);
	}
}
