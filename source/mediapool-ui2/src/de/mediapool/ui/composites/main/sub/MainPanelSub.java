package de.mediapool.ui.composites.main.sub;

import java.util.Map;

import com.vaadin.ui.Component;

public interface MainPanelSub {

	public void setParameter(Map<String, String> pParameter);

	public Map<String, String> getParameter();

	public Component getComponent();

	public String getName();
}
