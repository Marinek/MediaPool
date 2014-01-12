package de.juma.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import de.juma.home.utils.ActivitySwipeDetector;

public class ReceiverControl extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.control);

		ScrollView layMain = (ScrollView) findViewById(R.id.layout_control);
		layMain.setOnTouchListener(new ActivitySwipeDetector(this, false));
	}

	public void switchView() {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		finish();

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
