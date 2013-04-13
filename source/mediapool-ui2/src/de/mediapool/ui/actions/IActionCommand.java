package de.mediapool.ui.actions;

import com.vaadin.data.util.BeanItem;

public interface IActionCommand {
	public void execute(BeanItem<?> lBeanItem);
	public boolean isActionCommandFor(String pActionCommand);
	public String getActionCommand();
}
