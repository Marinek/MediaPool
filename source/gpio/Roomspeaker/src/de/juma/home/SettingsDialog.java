package de.juma.home;

import com.example.testhtc.R;

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

public class SettingsDialog extends Dialog implements OnClickListener {

	private Button cancel_Button;
	private Button backup_Button;
	private Button save_Button;
	private Roomspeaker context;
	private SharedPreferences prefs;
	private String urlString = "com.example.testhtc.url";
	private String refreshString = "com.example.testhtc.refresh";

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

	private void saveSettings() {

		String new_url = ((TextView) findViewById(R.id.settings_url_text)).getText().toString();
		String new_refreshTime = ((TextView) findViewById(R.id.settings_refreshtime_text)).getText().toString();
		changeSettings(new_refreshTime, new_url);

		printAsToast("Einstellungen gespeichert");
		context.restartTimer();

		this.cancel();
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
