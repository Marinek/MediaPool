package de.mediapool.core.beans.business.entity.action;

public enum EActions {
	
	OPEN("open","Öffnen"),
	SAVE("save","Speichern"),
	ADD("add","Hinzufügen");
	
	private String actionCommand = null;
	private String displayedText = null;
	
	private EActions(String pActionCommand, String pDisplayedText){
		this.setActionCommand(pActionCommand);
		this.setDisplayedText(pDisplayedText);
	}

	public String getActionCommand() {
		return actionCommand;
	}

	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}

	public String getDisplayedText() {
		return displayedText;
	}

	public void setDisplayedText(String displayedText) {
		this.displayedText = displayedText;
	}
	
	

}
