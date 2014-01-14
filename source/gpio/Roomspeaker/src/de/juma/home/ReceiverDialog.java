package de.juma.home;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import de.juma.home.utils.RS_Interface;
import de.juma.home.utils.RS_OntouchListener;
import de.juma.home.utils.RS_RestConnection;

public class ReceiverDialog extends Activity implements OnClickListener, RS_Interface {

	private ScrollView layMain;

	private Button rc_fm_button1;
	private Button rc_fm_button2;
	private Button rc_fm_button3;
	private Button rc_fm_button4;
	private Button rc_fm_button5;
	private Button rc_main_button1;
	private Button rc_main_button2;
	private Button rc_main_button3;
	private Button rc_main_button4;
	private Button rc_main_button5;
	private ImageButton rc_vol_button1;
	private ImageButton rc_vol_button2;
	private ImageButton rc_vol_button3;

	private List<Button> main_toggleList;
	private List<Button> fm_toggleList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiver);
		intiButtons();
		layMain = (ScrollView) findViewById(R.id.layout_receiver);
		layMain.setOnTouchListener(new RS_OntouchListener(this, false));
	}

	@Override
	public void switchView(boolean right) {
		Intent i = new Intent(this, SpeakerDialog.class);
		int anim_id = right ? R.anim.slide_out_right : R.anim.slide_out_left;
		layMain.setAnimation(AnimationUtils.loadAnimation(this, anim_id));
		startActivity(i);
	}

	@Override
	public void onClick(View view) {
		int button_id = view.getId();
		List<Button> toggleList = null;
		Log.w("onclick", button_id + "");
		switch (button_id) {
		case (R.id.rc_fm_button1):
			toggleList = fm_toggleList;
			break;
		case (R.id.rc_fm_button2):
			toggleList = fm_toggleList;
			break;
		case (R.id.rc_fm_button3):
			toggleList = fm_toggleList;
			break;
		case (R.id.rc_fm_button4):
			toggleList = fm_toggleList;
			break;
		case (R.id.rc_fm_button5):
			toggleList = fm_toggleList;
			break;
		case (R.id.rc_main_button1):
			toggleList = main_toggleList;
			lockFMList(true);
			break;
		case (R.id.rc_main_button2):
			toggleList = main_toggleList;
			lockFMList(false);
			break;
		case (R.id.rc_main_button3):
			toggleList = main_toggleList;
			lockFMList(false);
			break;
		case (R.id.rc_main_button4):
			toggleList = main_toggleList;
			lockFMList(false);
			break;
		case (R.id.rc_main_button5):
			toggleList = main_toggleList;
			lockFMList(false);
			break;
		case (R.id.rc_vol_button1):
			break;
		case (R.id.rc_vol_button2):
			break;
		case (R.id.rc_vol_button3):
			break;
		default:
			break;
		}
		if (toggleList != null) {
			resetList(toggleList);
			Button bt = (Button) findViewById(button_id);
			bt.setBackgroundResource(R.drawable.custom_btn_red);
			bt.refreshDrawableState();
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

	private void resetList(List<Button> resetList) {
		for (Button b : resetList) {
			b.setBackgroundResource(R.drawable.custom_btn_green);
			b.refreshDrawableState();
		}
	}

	private void lockFMList(boolean lock) {
		for (Button b : fm_toggleList) {
			b.setClickable(lock);
		}
	}

	@Override
	public void startRestRequest(int button_id, String param, Activity context) {
		new RS_RestConnection(button_id, param, context).execute();

	}

	private void intiButtons() {
		main_toggleList = new ArrayList<Button>();
		fm_toggleList = new ArrayList<Button>();

		rc_fm_button1 = (Button) findViewById(R.id.rc_fm_button1);
		rc_fm_button2 = (Button) findViewById(R.id.rc_fm_button2);
		rc_fm_button3 = (Button) findViewById(R.id.rc_fm_button3);
		rc_fm_button4 = (Button) findViewById(R.id.rc_fm_button4);
		rc_fm_button5 = (Button) findViewById(R.id.rc_fm_button5);
		rc_main_button1 = (Button) findViewById(R.id.rc_main_button1);
		rc_main_button2 = (Button) findViewById(R.id.rc_main_button2);
		rc_main_button3 = (Button) findViewById(R.id.rc_main_button3);
		rc_main_button4 = (Button) findViewById(R.id.rc_main_button4);
		rc_main_button5 = (Button) findViewById(R.id.rc_main_button5);
		rc_vol_button1 = (ImageButton) findViewById(R.id.rc_vol_button1);
		rc_vol_button2 = (ImageButton) findViewById(R.id.rc_vol_button2);
		rc_vol_button3 = (ImageButton) findViewById(R.id.rc_vol_button3);
		rc_fm_button1.setOnClickListener(this);
		rc_fm_button2.setOnClickListener(this);
		rc_fm_button3.setOnClickListener(this);
		rc_fm_button4.setOnClickListener(this);
		rc_fm_button5.setOnClickListener(this);
		rc_main_button1.setOnClickListener(this);
		rc_main_button2.setOnClickListener(this);
		rc_main_button3.setOnClickListener(this);
		rc_main_button4.setOnClickListener(this);
		rc_main_button5.setOnClickListener(this);
		rc_vol_button1.setOnClickListener(this);
		rc_vol_button2.setOnClickListener(this);
		rc_vol_button3.setOnClickListener(this);

		main_toggleList.add(rc_main_button1);
		main_toggleList.add(rc_main_button2);
		main_toggleList.add(rc_main_button3);
		main_toggleList.add(rc_main_button4);
		main_toggleList.add(rc_main_button5);

		fm_toggleList.add(rc_fm_button1);
		fm_toggleList.add(rc_fm_button2);
		fm_toggleList.add(rc_fm_button3);
		fm_toggleList.add(rc_fm_button4);
		fm_toggleList.add(rc_fm_button5);
	}
}
