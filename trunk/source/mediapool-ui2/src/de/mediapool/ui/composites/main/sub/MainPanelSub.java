package de.mediapool.ui.composites.main.sub;

import java.util.Map;

import com.vaadin.ui.Component;

public abstract class MainPanelSub {

	private Map<String, String> parameter;

	public void setParameter(Map<String, String> pParameter) {
		this.parameter = pParameter;
	}

	public String getParameter(String pName) {
		return parameter.get(pName);
	}

	public abstract Component getComponent();

	public abstract String getName();
}
