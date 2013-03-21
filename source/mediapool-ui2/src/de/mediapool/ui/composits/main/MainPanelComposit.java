package de.mediapool.ui.composits.main;

import java.util.Map;

import com.vaadin.ui.Component;

public interface MainPanelComposit {

	public void setParameter(Map<String, String> pParameter);

	public Map<String, String> getParameter();

	public Component getComponent();

	public String getName();
}
