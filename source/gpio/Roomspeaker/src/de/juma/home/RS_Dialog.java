package de.juma.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import de.juma.home.utils.RS_Interface;
import de.juma.home.utils.RS_LittleHelper;
import de.juma.home.utils.RS_RestConnection;

public class RS_Dialog extends Activity implements OnClickListener, RS_Interface {

	protected RS_LittleHelper lh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lh = new RS_LittleHelper(this);
	}

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
	public void startRestRequest(int button_id, String param, Activity context) {
		new RS_RestConnection(button_id, param, context).execute();

	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_refresh:
			lh.refreshStatusFromServer();
			return true;
		case R.id.menu_preferences:
			lh.showSettingsDialog();
			return true;
		case R.id.menu_exit:
			System.exit(0);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void setResultFromServer(String result, int button_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlockButton(int id, boolean lock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshWithTimer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchView(boolean right) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
