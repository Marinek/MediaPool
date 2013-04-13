package de.mediapool.ui.actions;

import org.apache.commons.lang.StringUtils;

import com.vaadin.data.util.BeanItem;

import de.mediapool.ui.actions.interfaces.IActionCommand;

public class ActionCommand implements IActionCommand {
	
	private static String ACTION_COMMAND = "save";
	
	public boolean isActionCommandFor(String pActionCommand) {
		return StringUtils.equals(ACTION_COMMAND, pActionCommand);
	}

	public String getActionCommand() {
		return ACTION_COMMAND;
	}

	public void execute(BeanItem<?> lBeanItem) {
		
	}

}