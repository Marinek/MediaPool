package de.juma.home;

import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.ToggleButton;
import de.juma.home.beans.GpioCode;
import de.juma.home.beans.GpioPin;
import de.juma.home.utils.RS_Interface;
import de.juma.home.utils.RS_LittleHelper;
import de.juma.home.utils.RS_OntouchListener;
import de.juma.home.utils.RS_RestConnection;

public class SpeakerDialog extends Activity implements OnClickListener, RS_Interface {

	private GpioCode gpioCode;

	private ToggleButton toggleButton1;
	private ToggleButton toggleButton2;
	private ToggleButton toggleButton3;
	private ToggleButton toggleButton4;

	private ToggleButton toggleButton5;
	private ToggleButton toggleButton6;
	private ToggleButton toggleButton7;
	private ToggleButton toggleButton8;

	private ToggleButton toggleButton9;
	private ToggleButton toggleButton10;
	private ToggleButton toggleButton11;
	private ToggleButton toggleButton12;

	private ToggleButton toggleButton13;
	private ToggleButton toggleButton14;
	private ToggleButton toggleButton15;

	private RS_LittleHelper lh;

	private Timer t;

	private final String CHANGE = "change/";
	private final String STATUS = "status";

	private ScrollView layMain;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.speaker);
		lh = new RS_LittleHelper(this);

		gpioCode = new GpioCode();

		initButtons();

		refreshStatusFromServer();

		t = lh.startTimer();

		layMain = (ScrollView) findViewById(R.id.layout_main);
		layMain.setOnTouchListener(new RS_OntouchListener(this, true));
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
	public void setResultFromServer(String result, int button_id) {
		if (button_id == R.id.menu_refresh) {
			gpioCode = new GpioCode(result);
		}

		refreshButtons();
	}

	public void restartTimer() {
		if (t != null) {
			t.cancel();
		}
		t = lh.startTimer();
	}

	private void showSettingsDialog() {
		final SettingsDialog dialog = new SettingsDialog(this);
		dialog.show();
	}

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
	public void refreshWithTimer() {
		refreshStatusFromServer();
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_refresh:
			refreshStatusFromServer();
			return true;
		case R.id.menu_preferences:
			showSettingsDialog();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void refreshStatusFromServer() {
		new RS_RestConnection(R.id.menu_refresh, STATUS, this).execute();
	}

	@Override
	public void onClick(View active_button) {
		int button_id = active_button.getId();
		String param = STATUS;

		GpioPin pin = null;
		if (gpioCode != null) {
			switch (button_id) {
			case (R.id.toggleButton1):
				Log.w("intent", "switch");
				gpioCode.setEGGroup(toggleButton1.isChecked());
				break;
			case (R.id.toggleButton2):
				pin = gpioCode.getPin2();
				gpioCode.setPin2(pin.switchTo(toggleButton2.isChecked()));
				break;
			case (R.id.toggleButton3):
				pin = gpioCode.getPin3();
				gpioCode.setPin3(pin.switchTo(toggleButton3.isChecked()));
				break;
			case (R.id.toggleButton4):
				pin = gpioCode.getPin4();
				gpioCode.setPin4(pin.switchTo(toggleButton4.isChecked()));
				break;
			case (R.id.toggleButton5):
				pin = gpioCode.getPin5();
				gpioCode.setPin5(pin.switchTo(toggleButton5.isChecked()));
				break;
			case (R.id.toggleButton6):
				gpioCode.setOGGroup(toggleButton6.isChecked());
				break;
			case (R.id.toggleButton7):
				pin = gpioCode.getPin7();
				gpioCode.setPin7(pin.switchTo(toggleButton7.isChecked()));
				break;
			case (R.id.toggleButton8):
				pin = gpioCode.getPin8();
				gpioCode.setPin8(pin.switchTo(toggleButton8.isChecked()));
				break;
			case (R.id.toggleButton9):
				pin = gpioCode.getPin9();
				gpioCode.setPin9(pin.switchTo(toggleButton9.isChecked()));
				break;
			case (R.id.toggleButton10):
				pin = gpioCode.getPin10();
				gpioCode.setPin10(pin.switchTo(toggleButton10.isChecked()));
				break;
			case (R.id.toggleButton11):
				gpioCode.setDGGroup(toggleButton11.isChecked());
				break;
			case (R.id.toggleButton12):
				pin = gpioCode.getPin12();
				gpioCode.setPin12(pin.switchTo(toggleButton12.isChecked()));
				break;
			case (R.id.toggleButton13):
				pin = gpioCode.getPin13();
				gpioCode.setPin13(pin.switchTo(toggleButton13.isChecked()));
				break;
			case (R.id.toggleButton14):
				pin = gpioCode.getPin14();
				gpioCode.setPin14(pin.switchTo(toggleButton14.isChecked()));
				break;
			case (R.id.toggleButton15):
				gpioCode.setAllGroup(toggleButton15.isChecked());
				break;
			default:
				;
			}
			param = CHANGE + gpioCode.toString();
		}

		refreshButtons();

		unlockButton(button_id, false);

		new RS_RestConnection(button_id, param, this).execute();

	}

	@Override
	public void unlockButton(int id, boolean unlock) {
		Button b = (Button) findViewById(id);
		if (b != null) {
			b.setClickable(unlock);
		}
		refreshButtons();
	}

	private void refreshButtons() {
		toggleButton1.setChecked(gpioCode.isEgPinsChecked());
		toggleButton6.setChecked(gpioCode.isOgPinsChecked());
		toggleButton11.setChecked(gpioCode.isDgPinsChecked());
		toggleButton15.setChecked(gpioCode.isAllPinsChecked());

		toggleButton2.setChecked(gpioCode.getPin2().isOn());
		toggleButton3.setChecked(gpioCode.getPin3().isOn());
		toggleButton4.setChecked(gpioCode.getPin4().isOn());
		toggleButton5.setChecked(gpioCode.getPin5().isOn());
		toggleButton7.setChecked(gpioCode.getPin7().isOn());
		toggleButton8.setChecked(gpioCode.getPin8().isOn());
		toggleButton9.setChecked(gpioCode.getPin9().isOn());
		toggleButton10.setChecked(gpioCode.getPin10().isOn());
		toggleButton12.setChecked(gpioCode.getPin12().isOn());
		toggleButton13.setChecked(gpioCode.getPin13().isOn());
		toggleButton14.setChecked(gpioCode.getPin14().isOn());
	}

	private void initButtons() {

		toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
		toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
		toggleButton4 = (ToggleButton) findViewById(R.id.toggleButton4);

		toggleButton5 = (ToggleButton) findViewById(R.id.toggleButton5);
		toggleButton6 = (ToggleButton) findViewById(R.id.toggleButton6);
		toggleButton7 = (ToggleButton) findViewById(R.id.toggleButton7);
		toggleButton8 = (ToggleButton) findViewById(R.id.toggleButton8);

		toggleButton9 = (ToggleButton) findViewById(R.id.toggleButton9);
		toggleButton10 = (ToggleButton) findViewById(R.id.toggleButton10);
		toggleButton11 = (ToggleButton) findViewById(R.id.toggleButton11);
		toggleButton12 = (ToggleButton) findViewById(R.id.toggleButton12);

		toggleButton13 = (ToggleButton) findViewById(R.id.toggleButton13);
		toggleButton14 = (ToggleButton) findViewById(R.id.toggleButton14);
		toggleButton15 = (ToggleButton) findViewById(R.id.toggleButton15);

		// status_button.setOnClickListener(this);

		toggleButton1.setOnClickListener(this);
		toggleButton2.setOnClickListener(this);
		toggleButton3.setOnClickListener(this);
		toggleButton4.setOnClickListener(this);

		toggleButton5.setOnClickListener(this);
		toggleButton6.setOnClickListener(this);
		toggleButton7.setOnClickListener(this);
		toggleButton8.setOnClickListener(this);

		toggleButton9.setOnClickListener(this);
		toggleButton10.setOnClickListener(this);
		toggleButton11.setOnClickListener(this);
		toggleButton12.setOnClickListener(this);

		toggleButton13.setOnClickListener(this);
		toggleButton14.setOnClickListener(this);
		toggleButton15.setOnClickListener(this);

	}

}
