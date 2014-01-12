package de.juma.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testhtc.R;

public class SettingsDialog extends Dialog implements OnClickListener {

	private Button cancel_Button;
	private Button backup_Button;
	private Button save_Button;
	private Roomspeaker context;
	private SharedPreferences prefs;
	private String urlString = "com.example.testhtc.url";
	private String refreshString = "com.example.testhtc.refresh";

	private static final String IP_REGEX = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";

	public SettingsDialog(Roomspeaker mainDialog) {
		super(mainDialog);
		this.context = mainDialog;
		setContentView(R.layout.settings);
		prefs = context.getSharedPreferences("com.example.testhtc", Context.MODE_PRIVATE);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

		initButtons();
	}

	@Override
	public void onClick(View v) {
		int button_id = v.getId();
		Log.w("settingsbutton", button_id + "");
		switch (button_id) {
		case (R.id.settings_save_button):
			saveSettings();
			break;
		case (R.id.settings_backup_button):
			resetSettings();
			break;
		default:
			this.cancel();
		}

	}

	private boolean validateFields() {

		TextView url_text = (TextView) findViewById(R.id.settings_url_text);
		String new_url = url_text.getText().toString();
		TextView url_error = (TextView) findViewById(R.id.settings_urlError);
		String fehlertext = "";
		boolean valid = true;
		if (!IsMatch(new_url, IP_REGEX)) {
			fehlertext = "Ungültige IP";
			valid = false;
		}
		url_error.setText(fehlertext);
		return valid;
	}

	private static boolean IsMatch(String s, String pattern) {
		try {
			Pattern patt = Pattern.compile(pattern);
			Matcher matcher = patt.matcher(s);
			return matcher.matches();
		} catch (RuntimeException e) {
			return false;
		}
	}

	private void saveSettings() {

		if (validateFields()) {
			String new_url = ((TextView) findViewById(R.id.settings_url_text)).getText().toString();
			String new_refreshTime = ((TextView) findViewById(R.id.settings_refreshtime_text)).getText().toString();
			changeSettings(new_refreshTime, new_url);
			printAsToast("Einstellungen gespeichert");
			context.restartTimer();
			this.cancel();
		}
	}

	private void resetSettings() {

		String default_url = context.getResources().getString(R.string.settings_url_text);
		String default_refreshTime = context.getResources().getString(R.string.settings_refreshtime_text);
		changeSettings(default_refreshTime, default_url);

		printAsToast("Einstellungen zurückgesetzt");
		this.cancel();
	}

	private void changeSettings(String refreshTime, String url) {
		SharedPreferences.Editor editor = prefs.edit();

		TextView url_text = (TextView) findViewById(R.id.settings_url_text);
		url_text.setText(url);

		TextView refreshTime_text = (TextView) findViewById(R.id.settings_refreshtime_text);
		refreshTime_text.setText(refreshTime);

		editor.putString(urlString, url_text.getText().toString());
		editor.putString(refreshString, refreshTime_text.getText().toString());

		editor.commit();

	}

	private void printAsToast(String message) {
		int duration = Toast.LENGTH_SHORT;
		Log.w("Toast", message.toString());
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}

	private void initButtons() {
		cancel_Button = (Button) findViewById(R.id.settings_cancel_button);
		backup_Button = (Button) findViewById(R.id.settings_backup_button);
		save_Button = (Button) findViewById(R.id.settings_save_button);

		cancel_Button.setOnClickListener(this);
		backup_Button.setOnClickListener(this);
		save_Button.setOnClickListener(this);

		TextView url_text = (TextView) findViewById(R.id.settings_url_text);
		url_text.setText(prefs.getString(urlString, context.getResources().getString(R.string.settings_url_text)));
		TextView refreshTime_text = (TextView) findViewById(R.id.settings_refreshtime_text);
		refreshTime_text.setText(prefs.getString(refreshString, context.getResources().getString(R.string.settings_refreshtime_text)));

	}
}
