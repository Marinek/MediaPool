package de.mediapool.ui.actions.commands;

import org.apache.commons.lang.StringUtils;

import com.vaadin.data.util.BeanItem;

import de.mediapool.ui.actions.ActionCommand;


public class SaveActionCommand implements ActionCommand {
	
	public static String ACTION_COMMAND = "save";
	
	public boolean isActionCommandFor(String pActionCommand) {
		return StringUtils.equals(ACTION_COMMAND, pActionCommand);
	}

	public String getActionCommand() {
		return ACTION_COMMAND;
	}

	public void execute(BeanItem<?> lBeanItem) {
		//TODO: SaveMethode in der Bean implementieren
		//lBeanItem.save( irgendwas );
		System.out.print("Bean save");
	}

}
