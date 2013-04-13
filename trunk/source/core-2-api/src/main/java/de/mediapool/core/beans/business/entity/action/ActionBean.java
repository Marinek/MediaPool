package de.mediapool.core.beans.business.entity.action;

import de.mediapool.core.beans.interfaces.IBean;

public class ActionBean implements IBean {

	private static final long serialVersionUID = 1L;
	
	private String icon = null;
	
	private String command = null;
	
	private String displayedtext = null;

	
	public ActionBean () {
		
	}
	
	public ActionBean (String command, String icon,String displayedtext) {
		this.command = command;
		this.icon = icon;
		this.displayedtext = displayedtext;
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

	public String getDisplayedtext() {
		return displayedtext;
	}

	public void setDisplayedtext(String displayedtext) {
		this.displayedtext = displayedtext;
	}

}
