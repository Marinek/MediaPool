package com.example.testhtc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.testhtc.bean.GpioCode;
import com.example.testhtc.bean.GpioPin;

public class Main extends Activity implements OnClickListener {

	private GpioCode gpioCode;

	Button off_button;
	Button on_button;
	Button status_button;

	Button pin1_button;
	Button pin2_button;
	Button pin3_button;
	Button pin4_button;

	Button pin5_button;
	Button pin6_button;
	Button pin7_button;
	Button pin8_button;

	Button pin9_button;
	Button pin10_button;
	Button pin11_button;
	Button pin12_button;

	Button pin13_button;
	Button pin14_button;
	Button pin15_button;
	Button pin16_button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		initButtons();
		new LongRunningGetIO(R.id.status_button, "status").execute();
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
			HttpGet httpGet = new HttpGet("http://192.168.0.170/gpio/" + param);
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
			if (results != null) {
				String output = results;

				if (button_id == R.id.status_button) {
					gpioCode = new GpioCode(results);
					output = gpioCode.toString();
				}

				Log.w("results", results);
				Log.w("gpio", gpioCode.toString());

				EditText et = (EditText) findViewById(R.id.my_edit);

				et.setText(output);
				paintButtons();
			}

			Button b = (Button) findViewById(button_id);
			b.setClickable(true);

		}
	}

	@Override
	public void onClick(View active_button) {
		int button_id;
		String param;
		GpioPin pin = null;
		switch (active_button.getId()) {
		case (R.id.off_button):
			button_id = R.id.off_button;
			param = "off";
			gpioCode.setAllPins("0000000000000000");
			break;
		case (R.id.on_button):
			button_id = R.id.on_button;
			param = "on";
			gpioCode.setAllPins("1111111111111111");
			break;
		case (R.id.pin1_button):
			button_id = R.id.pin1_button;
			pin = gpioCode.getPin1();
			gpioCode.setPin1(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin2_button):
			button_id = R.id.pin2_button;
			pin = gpioCode.getPin2();
			gpioCode.setPin2(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin3_button):
			button_id = R.id.pin3_button;
			pin = gpioCode.getPin3();
			gpioCode.setPin3(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin4_button):
			button_id = R.id.pin4_button;
			pin = gpioCode.getPin4();
			gpioCode.setPin4(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;

		case (R.id.pin5_button):
			button_id = R.id.pin5_button;
			pin = gpioCode.getPin5();
			gpioCode.setPin5(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin6_button):
			button_id = R.id.pin6_button;
			pin = gpioCode.getPin6();
			gpioCode.setPin6(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin7_button):
			button_id = R.id.pin7_button;
			pin = gpioCode.getPin7();
			gpioCode.setPin7(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin8_button):
			button_id = R.id.pin8_button;
			pin = gpioCode.getPin8();
			gpioCode.setPin8(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;

		case (R.id.pin9_button):
			button_id = R.id.pin9_button;
			pin = gpioCode.getPin9();
			gpioCode.setPin9(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin10_button):
			button_id = R.id.pin10_button;
			pin = gpioCode.getPin10();
			gpioCode.setPin10(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin11_button):
			button_id = R.id.pin11_button;
			pin = gpioCode.getPin11();
			gpioCode.setPin11(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin12_button):
			button_id = R.id.pin12_button;
			pin = gpioCode.getPin12();
			gpioCode.setPin12(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;

		case (R.id.pin13_button):
			button_id = R.id.pin13_button;
			pin = gpioCode.getPin13();
			gpioCode.setPin13(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin14_button):
			button_id = R.id.pin14_button;
			pin = gpioCode.getPin14();
			gpioCode.setPin14(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin15_button):
			button_id = R.id.pin15_button;
			pin = gpioCode.getPin15();
			gpioCode.setPin15(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		case (R.id.pin16_button):
			button_id = R.id.pin16_button;
			pin = gpioCode.getPin16();
			gpioCode.setPin16(pin.switchOutput());
			param = "change/" + gpioCode.toString();
			break;
		default:
			button_id = R.id.status_button;
			param = "status";
			;
		}

		paintButtons();
		Button b = (Button) findViewById(button_id);
		b.setClickable(false);

		new LongRunningGetIO(button_id, param).execute();

	}

	private void paintButtons() {
		if (gpioCode != null) {
			pin1_button.setBackgroundColor(gpioCode.getPin1().isOn() ? Color.GREEN : Color.RED);
			pin2_button.setBackgroundColor(gpioCode.getPin2().isOn() ? Color.GREEN : Color.RED);
			pin3_button.setBackgroundColor(gpioCode.getPin3().isOn() ? Color.GREEN : Color.RED);
			pin4_button.setBackgroundColor(gpioCode.getPin4().isOn() ? Color.GREEN : Color.RED);

			pin5_button.setBackgroundColor(gpioCode.getPin5().isOn() ? Color.GREEN : Color.RED);
			pin6_button.setBackgroundColor(gpioCode.getPin6().isOn() ? Color.GREEN : Color.RED);
			pin7_button.setBackgroundColor(gpioCode.getPin7().isOn() ? Color.GREEN : Color.RED);
			pin8_button.setBackgroundColor(gpioCode.getPin8().isOn() ? Color.GREEN : Color.RED);

			pin9_button.setBackgroundColor(gpioCode.getPin9().isOn() ? Color.GREEN : Color.RED);
			pin10_button.setBackgroundColor(gpioCode.getPin10().isOn() ? Color.GREEN : Color.RED);
			pin11_button.setBackgroundColor(gpioCode.getPin11().isOn() ? Color.GREEN : Color.RED);
			pin12_button.setBackgroundColor(gpioCode.getPin12().isOn() ? Color.GREEN : Color.RED);

			pin13_button.setBackgroundColor(gpioCode.getPin13().isOn() ? Color.GREEN : Color.RED);
			pin14_button.setBackgroundColor(gpioCode.getPin14().isOn() ? Color.GREEN : Color.RED);
			pin15_button.setBackgroundColor(gpioCode.getPin15().isOn() ? Color.GREEN : Color.RED);
			pin16_button.setBackgroundColor(gpioCode.getPin16().isOn() ? Color.GREEN : Color.RED);
		}
	}

	private void initButtons() {
		off_button = (Button) findViewById(R.id.off_button);
		on_button = (Button) findViewById(R.id.on_button);
		status_button = (Button) findViewById(R.id.status_button);

		pin1_button = (Button) findViewById(R.id.pin1_button);
		pin2_button = (Button) findViewById(R.id.pin2_button);
		pin3_button = (Button) findViewById(R.id.pin3_button);
		pin4_button = (Button) findViewById(R.id.pin4_button);

		pin5_button = (Button) findViewById(R.id.pin5_button);
		pin6_button = (Button) findViewById(R.id.pin6_button);
		pin7_button = (Button) findViewById(R.id.pin7_button);
		pin8_button = (Button) findViewById(R.id.pin8_button);

		pin9_button = (Button) findViewById(R.id.pin9_button);
		pin10_button = (Button) findViewById(R.id.pin10_button);
		pin11_button = (Button) findViewById(R.id.pin11_button);
		pin12_button = (Button) findViewById(R.id.pin12_button);

		pin13_button = (Button) findViewById(R.id.pin13_button);
		pin14_button = (Button) findViewById(R.id.pin14_button);
		pin15_button = (Button) findViewById(R.id.pin15_button);
		pin16_button = (Button) findViewById(R.id.pin16_button);

		off_button.setOnClickListener(this);
		on_button.setOnClickListener(this);
		status_button.setOnClickListener(this);

		pin1_button.setOnClickListener(this);
		pin2_button.setOnClickListener(this);
		pin3_button.setOnClickListener(this);
		pin4_button.setOnClickListener(this);

		pin5_button.setOnClickListener(this);
		pin6_button.setOnClickListener(this);
		pin7_button.setOnClickListener(this);
		pin8_button.setOnClickListener(this);

		pin9_button.setOnClickListener(this);
		pin10_button.setOnClickListener(this);
		pin11_button.setOnClickListener(this);
		pin12_button.setOnClickListener(this);

		pin13_button.setOnClickListener(this);
		pin14_button.setOnClickListener(this);
		pin15_button.setOnClickListener(this);
		pin16_button.setOnClickListener(this);
	}

}
