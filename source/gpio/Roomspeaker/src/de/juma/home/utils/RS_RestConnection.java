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

public class RS_RestConnection extends AsyncTask<Void, Void, String> {

	private int button_id;
	private String param;
	private RS_LittleHelper lh;

	public RS_RestConnection(int button_id, String param, Activity context) {
		super();
		this.button_id = button_id;
		this.param = param;
		this.lh = new RS_LittleHelper(context);
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
		Log.w("url", lh.createConnectionString(param));
		String result = null;
		try {

			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			result = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();

		}

		return result;

	}

	protected void onPostExecute(String results) {
		RS_Interface jmr = ((RS_Interface) (lh.getContext()));
		if ((results != null) && (!results.equals("No route to host")) && (!results.equals("http"))) {

			jmr.setResultFromServer(results, button_id);

			Log.w("results", results);

		} else {
			lh.printAsToast(R.string.server_error);
		}
		jmr.unlockButton(button_id, true);

	}
}
