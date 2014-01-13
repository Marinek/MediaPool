package de.juma.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ScrollView;
import de.juma.home.utils.RS_Interface;
import de.juma.home.utils.RS_OntouchListener;

public class ReceiverDialog extends Activity implements OnClickListener, RS_Interface {

	private ScrollView layMain;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.receiver);

		layMain = (ScrollView) findViewById(R.id.layout_receiver);
		layMain.setOnTouchListener(new RS_OntouchListener(this, false));
	}

	@Override
	public void switchView(boolean right) {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		int anim_id = right ? R.anim.slide_out_right : R.anim.slide_out_left;
		layMain.setAnimation(AnimationUtils.loadAnimation(this, anim_id));
		finish();

	}

	@Override
	public void onClick(View view) {
		int button_id = view.getId();
		switch (button_id) {
		case (R.string.rc_fm_button1):
			break;
		case (R.string.rc_fm_button2):
			break;
		case (R.string.rc_fm_button3):
			break;
		case (R.string.rc_fm_button4):
			break;
		case (R.string.rc_fm_button5):
			break;
		case (R.string.rc_main_button1):
			break;
		case (R.string.rc_main_button2):
			break;
		case (R.string.rc_main_button3):
			break;
		case (R.string.rc_main_button4):
			break;
		case (R.string.rc_main_button5):
			break;
		case (R.string.rc_vol_button1):
			break;
		case (R.string.rc_vol_button2):
			break;
		case (R.string.rc_vol_button3):
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
	public void setResultFromServer(String result, int button_id) {

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