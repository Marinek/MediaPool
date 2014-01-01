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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CopyOfMain extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		Button off_button = (Button) findViewById(R.id.off_button);
		Button on_button = (Button) findViewById(R.id.on_button);

		off_button.setOnClickListener(this);
		on_button.setOnClickListener(this);

	}

	@Override
	public void onClick(View active_button) {
		int button_id;
		String param;
		switch (active_button.getId()) {
		case (R.id.off_button):
			button_id = R.id.off_button;
			param = "off";
			break;
		case (R.id.on_button):
			button_id = R.id.on_button;
			param = "on";
			break;
		default:
			button_id = R.id.off_button;
			param = "off";
			;
		}

		Button b = (Button) findViewById(button_id);
		b.setClickable(true);
		new LongRunningGetIO(button_id, param).execute();

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

				EditText et = (EditText) findViewById(R.id.my_edit);

				et.setText(results + button_id);

			}

			Button b = (Button) findViewById(button_id);
			b.setClickable(true);

		}

	}

}
