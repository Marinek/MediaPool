package de.juma.home.utils;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import de.juma.home.R;

public class RS_LittleHelper {
	private Activity context;
	private SharedPreferences prefs;
	private final String urlString = "de.juma.home.url";
	private final String refreshString = "de.juma.home.refresh";

	private final String IP_REGEX = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";

	private final String BASE_URL_START = "http://";
	private final String BASE_URL_END = "/gpio/";

	public RS_LittleHelper(Activity context) {
		super();
		this.context = context;
		this.prefs = context.getSharedPreferences("de.juma.home", Context.MODE_PRIVATE);
	}

	public void printAsToast(String message) {
		int duration = Toast.LENGTH_SHORT;
		Log.w("Toast", message.toString());
		Toast toast = Toast.makeText(getContext(), message, duration);
		toast.show();
	}

	public boolean isMatch(String s, String pattern) {
		try {
			Pattern patt = Pattern.compile(pattern);
			Matcher matcher = patt.matcher(s);
			return matcher.matches();
		} catch (RuntimeException e) {
			return false;
		}
	}

	public String createConnectionString(String param) {
		String newParam = param == null ? "" : param;
		String CUSTOM_URL = prefs.getString(urlString, context.getResources().getString(R.string.settings_url_text));
		return BASE_URL_START + CUSTOM_URL + BASE_URL_END + newParam;

	}

	public long getRefreshTime() {
		String default_value = getStringConstant(R.string.settings_refreshtime_text);
		String refresh_time = getPrefs().getString(getRefreshString(), default_value);
		long timerDelay = Long.parseLong(default_value);
		try {
			timerDelay = Long.parseLong(refresh_time);
		} catch (NumberFormatException e) {
			Log.w("RefreshTimmer", e.getLocalizedMessage());

		}
		long timerDelayMS = timerDelay * 1000;
		return timerDelayMS;

	}

	public String getCurrentTimeAsString() {
		long millis = System.currentTimeMillis();
		int seconds = (int) (millis / 1000);
		int minutes = seconds / 60;
		seconds = seconds % 60;
		return String.format(Locale.GERMANY, "%d:%02d", minutes, seconds);
	}

	public Timer startTimer() {
		long custom_delay = getRefreshTime();
		Timer t = null;
		if (custom_delay != 0) {
			t = new Timer();

			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					context.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Log.w("delay", getRefreshTime() + "");
							((RS_Interface) context).refreshWithTimer();
						}
					});
				}
			};

			t.scheduleAtFixedRate(task, 0, custom_delay);
			Log.w("newdelay", custom_delay + "");
		}
		return t;
	};

	public String getStringConstant(int stringID) {
		return context.getResources().getString(stringID);
	}

	public Activity getContext() {
		return context;
	}

	public SharedPreferences getPrefs() {
		return prefs;
	}

	public void setPrefs(SharedPreferences prefs) {
		this.prefs = prefs;
	}

	public String getRefreshString() {
		return refreshString;
	}

	public String getUrlString() {
		return urlString;
	}

	public String getIP_REGEX() {
		return IP_REGEX;
	}

}
