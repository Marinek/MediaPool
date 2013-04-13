package de.mediapool.ui.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.mediapool.ui.actions.commands.SaveActionCommand;


public class ActionCommandManager{

	private static ActionCommandManager manager = null;
	private Map<String,ActionCommand> actionCommands = null;
	
	private ActionCommandManager(){
		this.init();
	}
	public static ActionCommandManager getCommandManagerInstance(){
		if(manager == null){
			manager = new ActionCommandManager();
		}
		return manager;
	}
	/**
	 * Funktion zum initialen Hinzufügen von Actionsklassen
	 */
	private void init() {
		this.actionCommands= new HashMap<String,ActionCommand>();
		this.actionCommands.put(SaveActionCommand.ACTION_COMMAND,new SaveActionCommand());
	}
	
	public ActionCommand getActionCommandFor(String pActionCommand){
		if(this.actionCommands.keySet().contains(pActionCommand)){
		 return this.actionCommands.get(pActionCommand);
		}
		return null;
	}
}
