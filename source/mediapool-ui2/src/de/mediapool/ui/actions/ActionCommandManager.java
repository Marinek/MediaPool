package de.mediapool.ui.actions;

import java.util.HashMap;
import java.util.Map;

import de.mediapool.ui.actions.commands.EmptyActionCommand;
import de.mediapool.ui.actions.commands.SaveActionCommand;

public class ActionCommandManager {

	private static ActionCommandManager manager = null;
	private Map<String, Class<? extends ActionCommand>> actionCommands = new HashMap<String, Class<? extends ActionCommand>>();

	private ActionCommandManager() {
		this.init();
	}

	public static ActionCommandManager getCommandManagerInstance() {
		if (manager == null) {
			manager = new ActionCommandManager();
		}
		return manager;
	}

	/**
	 * Funktion zum initialen Hinzufügen von Actionsklassen
	 */
	private void init() {
		this.actionCommands.put(SaveActionCommand.ACTION_COMMAND, SaveActionCommand.class);
	}

	public ActionCommand getActionCommandFor(String pActionCommand) {
		if (this.actionCommands.keySet().contains(pActionCommand)) {
			return this.activate(this.actionCommands.get(pActionCommand));
		}
		return new EmptyActionCommand();
	}

	private ActionCommand activate(Class<? extends ActionCommand> actionCommandClass) {
		try {
			return actionCommandClass.newInstance();
		} catch (Throwable e) {
			// TODO: Exception
		}
		// TODO: Exception!!!
		return null;

	}
}
