package de.mediapool.ui.actions;
import com.vaadin.event.Action;
import com.vaadin.server.Resource;

public class MPAction extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MPAction(String caption) {
		super(caption);
	}
	public MPAction(String caption, Resource icon,String actioncommand) {
      super(caption,icon);
      this.actioncommand = actioncommand;
    }
	private String actioncommand= null;

	public String getActioncommand() {
		return actioncommand;
	}
	public void setActioncommand(String actioncommand) {
		this.actioncommand = actioncommand;
	}

}
