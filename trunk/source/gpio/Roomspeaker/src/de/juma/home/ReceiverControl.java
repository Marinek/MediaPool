package de.juma.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import de.juma.home.utils.RS_OntouchListener;
import de.juma.home.utils.RS_Interface;

public class ReceiverControl extends Activity implements OnClickListener, RS_Interface {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.control);

		ScrollView layMain = (ScrollView) findViewById(R.id.layout_control);
		layMain.setOnTouchListener(new RS_OntouchListener(this, false));
	}

	@Override
	public void switchView() {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		finish();

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
	public void setResultFromServer(String result) {

	}

	@Override
	public void unlockButton(int id, boolean unlock) {
		Button b = (Button) findViewById(id);
		if (b != null) {
			b.setClickable(unlock);
		}

	}

	@Override
	public void refreshWithTimer() {

	}

}
