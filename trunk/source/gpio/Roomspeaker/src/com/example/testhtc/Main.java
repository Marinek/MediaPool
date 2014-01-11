package com.example.testhtc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.testhtc.bean.GpioCode;
import com.example.testhtc.bean.GpioPin;

public class Main extends Activity implements OnClickListener {

	private GpioCode gpioCode;

	private SharedPreferences prefs;

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

	private Context context;

	Timer t;

	private final String CHANGE = "change/";
	private final String STATUS = "status";
	private final String BASE_URL_START = "http://";
	private final String BASE_URL_END = "/gpio/";

	private final String SERVER_ERROR = "Der Server ist nicht nicht erreichbar";

	private String urlString = "com.example.testhtc.url";

	private String refreshString = "com.example.testhtc.refresh";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		context = getApplicationContext();

		prefs = context.getSharedPreferences("com.example.testhtc", Context.MODE_PRIVATE);

		gpioCode = new GpioCode();

		initButtons();

		refreshStatusFromServer();

		startTimer();

	}

	private void startTimer() {
		long custom_delay = getRefreshTime();
		if (custom_delay != 0) {
			t = new Timer();

			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							long millis = System.currentTimeMillis();
							int seconds = (int) (millis / 1000);
							int minutes = seconds / 60;
							seconds = seconds % 60;
							Log.w("Timer", String.format("%d:%02d", minutes, seconds));
							Log.w("delay", getRefreshTime() + "");
							refreshStatusFromServer();

						}
					});
				}
			};

			t.scheduleAtFixedRate(task, 0, custom_delay);
			Log.w("newdelay", custom_delay + "");
		}
	};

	public void restartTimer() {
		if (t != null) {
			t.cancel();
		}
		startTimer();
	}

	private String createConnectionString(String param) {
		String newParam = param == null ? "" : param;
		String CUSTOM_URL = prefs.getString(urlString, context.getResources().getString(R.string.settings_url_text));
		return BASE_URL_START + CUSTOM_URL + BASE_URL_END + newParam;

	}

	private long getRefreshTime() {
		String default_value = context.getResources().getString(R.string.settings_refreshtime_text);
		String refresh_time = prefs.getString(refreshString, default_value);
		long timerDelay = Long.parseLong(default_value);
		try {
			timerDelay = Long.parseLong(refresh_time);
		} catch (NumberFormatException e) {
			Log.w("RefreshTimmer", e.getLocalizedMessage());

		}
		long timerDelayMS = timerDelay * 1000;
		return timerDelayMS;

	}

	private void showSettingsDialog() {
		// custom dialog
		final SettingsDialog dialog = new SettingsDialog(this);

		dialog.show();
	}

	private void printAsToast(String message) {
		int duration = Toast.LENGTH_SHORT;
		Log.w("Toast", message.toString());
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
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
		new LongRunningGetIO(R.id.menu_refresh, STATUS).execute();
	}

	private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

		int button_id;
		String param;

		public LongRunningGetIO(int button_id, String param) {
			super();
			this.button_id = button_id;
			this.param = param;
		}

		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {

			InputStream in = entity.getContent();

			StringBuffer out = new StringBuffer();
			int n = 1;
			while (n > 0) {
				byte[] b = new byte[4096];

				n = in.read(b);

				if (n > 0)
					out.append(new String(b, 0, n));

			}

			return out.toString();

		}

		@Override
		protected String doInBackground(Void... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet(createConnectionString(param));
			Log.w("url", createConnectionString(param));
			String text = null;
			try {

				HttpResponse response = httpClient.execute(httpGet, localContext);

				HttpEntity entity = response.getEntity();

				text = getASCIIContentFromEntity(entity);

			} catch (Exception e) {
				return e.getLocalizedMessage();

			}

			return text;

		}

		protected void onPostExecute(String results) {
			if ((results != null) && (!results.equals("No route to host")) && (!results.equals("http"))) {

				if (button_id == R.id.menu_refresh) {
					gpioCode = new GpioCode(results);
				}

				Log.w("results", results);
				Log.w("gpio", gpioCode.toString());

				refreshButtons();

			} else {
				printAsToast(SERVER_ERROR);
			}

			Button b = (Button) findViewById(button_id);
			if (b != null) {
				b.setClickable(true);
			}
		}
	}

	@Override
	public void onClick(View active_button) {
		int button_id = active_button.getId();
		String param = STATUS;

		GpioPin pin = null;
		if (gpioCode != null) {
			switch (button_id) {
			case (R.id.toggleButton1):
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

		Button b = (Button) findViewById(button_id);
		b.setClickable(false);

		new LongRunningGetIO(button_id, param).execute();

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
