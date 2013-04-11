package de.mediapool.core.beans.business.entity.action;

import de.mediapool.core.beans.interfaces.IBean;

public class ActionBean implements IBean {

	private static final long serialVersionUID = 1L;
	
	private String icon = null;
	
	private String command = null;

	
	public ActionBean () {
		
	}
	
	public ActionBean (String command, String icon) {
		this.command = command;
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
