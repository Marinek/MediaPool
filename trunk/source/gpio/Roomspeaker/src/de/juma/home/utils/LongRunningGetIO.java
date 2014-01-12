package de.juma.home.utils;

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
import android.util.Log;
import de.juma.home.R;

public class LongRunningGetIO extends AsyncTask<Void, Void, String> {

	private int button_id;
	private String param;
	private LittleHelper lh;

	public LongRunningGetIO(int button_id, String param, Activity context) {
		super();
		this.button_id = button_id;
		this.param = param;
		this.lh = new LittleHelper(context);
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
		HttpGet httpGet = new HttpGet(lh.createConnectionString(param));
		// Log.w("url", lh.createConnectionString(param));
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
		JumaRestService jmr = ((JumaRestService) (lh.getContext()));
		if ((results != null) && (!results.equals("No route to host")) && (!results.equals("http"))) {

			if (button_id == R.id.menu_refresh) {
				jmr.setResultFromServer(results);
			}

			Log.w("results", results);

		} else {
			lh.printAsToast(lh.getStringConstant(R.string.server_error));
		}
		jmr.unlockButton(button_id, true);

	}
}
