package de.juma.home.utils;

import android.app.Activity;
import android.view.Menu;

public interface RS_Interface {
	public void setResultFromServer(String result, int button_id);

	public void unlockButton(int id, boolean lock);

	public void refreshWithTimer();

	public void switchView(boolean right);

	public boolean onCreateOptionsMenu(Menu menu);

	public void startRestRequest(int button_id, String param, Activity context);

}
