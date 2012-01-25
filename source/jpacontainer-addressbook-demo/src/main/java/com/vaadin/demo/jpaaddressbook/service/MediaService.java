package com.vaadin.demo.jpaaddressbook.service;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.demo.jpaaddressbook.JpaAddressbookApplication;
import com.vaadin.demo.jpaaddressbook.domain.PMember;
import com.vaadin.demo.jpaaddressbook.domain.Participation;

public class MediaService {

	public static JPAContainer<Participation> getAllParticipation() {
		return JPAContainerFactory.make(Participation.class, JpaAddressbookApplication.PERSISTENCE_UNIT);
	}

	public static JPAContainer<PMember> getAllPMember() {
		return JPAContainerFactory.make(PMember.class, JpaAddressbookApplication.PERSISTENCE_UNIT);
	}
}
