package de.juma.home.utils;

import android.view.Menu;

public interface RS_Interface {
	public void setResultFromServer(String result);

	public void unlockButton(int id, boolean lock);

	public void refreshWithTimer();

	public void switchView(boolean right);

	public boolean onCreateOptionsMenu(Menu menu);
}
