package de.mediapool.ui.actions;

import java.util.ArrayList;

import de.mediapool.ui.actions.interfaces.IActionCommand;

public class ActionCommandManager{

	private static ActionCommandManager manager = null;
	private ArrayList<IActionCommand> actionCommands = null;
	
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
		this.actionCommands= new ArrayList<IActionCommand>();
		this.actionCommands.add(new SaveActionCommand());
	}
	
	public IActionCommand getActionCommandFor(String pActionCommand){
		for(IActionCommand actionCommand : this.actionCommands){
			if(actionCommand.isActionCommandFor(pActionCommand)){
				return actionCommand;
			}
		}
		return null;
	}
}
