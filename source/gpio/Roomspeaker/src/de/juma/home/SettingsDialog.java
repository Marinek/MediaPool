package de.juma.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import de.juma.home.utils.RS_LittleHelper;

public class SettingsDialog extends Dialog implements OnClickListener {

	private Button cancel_Button;
	private Button reset_Button;
	private Button save_Button;
	private RS_LittleHelper lh;

	public SettingsDialog(Activity mainDialog) {
		super(mainDialog);
		lh = new RS_LittleHelper(mainDialog);
		setContentView(R.layout.settings);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

		initButtons();
	}

	@Override
	public void onClick(View v) {
		int button_id = v.getId();
		switch (button_id) {
		case (R.id.settings_save_button):
			saveSettings();
			break;
		case (R.id.settings_reset_button):
			resetSettings();
			break;
		default:
			lh.printAsToast(R.string.settings_cancel_toast);
			this.cancel();
		}
	}

	private boolean validateUlrField() {

		TextView url_text = (TextView) findViewById(R.id.settings_url_text);
		String new_url = url_text.getText().toString();
		TextView url_error = (TextView) findViewById(R.id.settings_urlError);
		String fehlertext = "";
		boolean valid = true;
		if (!lh.isMatch(new_url, lh.getIP_REGEX())) {
			fehlertext = lh.getStringConstant(R.string.settings_url_error);
			valid = false;
		}
		url_error.setText(fehlertext);
		return valid;
	}

	private void saveSettings() {

		if (validateUlrField()) {
			String new_url = ((TextView) findViewById(R.id.settings_url_text)).getText().toString();
			String new_refreshTime = ((TextView) findViewById(R.id.settings_refreshtime_text)).getText().toString();
			changeSettings(new_refreshTime, new_url);
			lh.printAsToast(R.string.settings_save_toast);
			((SpeakerDialog) lh.getContext()).restartTimer();
			this.cancel();
		}
	}

	private void resetSettings() {

		String default_url = lh.getStringConstant(R.string.settings_url_text);
		String default_refreshTime = lh.getStringConstant(R.string.settings_refreshtime_text);
		changeSettings(default_refreshTime, default_url);

		lh.printAsToast(R.string.settings_reset_toast);
		this.cancel();
	}

	private void changeSettings(String refreshTime, String url) {
		SharedPreferences.Editor editor = lh.getPrefs().edit();

		TextView url_text = (TextView) findViewById(R.id.settings_url_text);
		url_text.setText(url);

		TextView refreshTime_text = (TextView) findViewById(R.id.settings_refreshtime_text);
		refreshTime_text.setText(refreshTime);

		editor.putString(lh.getUrlString(), url_text.getText().toString());
		editor.putString(lh.getRefreshString(), refreshTime_text.getText().toString());

		editor.commit();

	}

	private void initButtons() {
		cancel_Button = (Button) findViewById(R.id.settings_cancel_button);
		reset_Button = (Button) findViewById(R.id.settings_reset_button);
		save_Button = (Button) findViewById(R.id.settings_save_button);

		cancel_Button.setOnClickListener(this);
		reset_Button.setOnClickListener(this);
		save_Button.setOnClickListener(this);

		TextView url_text = (TextView) findViewById(R.id.settings_url_text);
		url_text.setText(lh.getPrefs().getString(lh.getUrlString(), lh.getStringConstant(R.string.settings_url_text)));
		TextView refreshTime_text = (TextView) findViewById(R.id.settings_refreshtime_text);
		refreshTime_text.setText(lh.getPrefs().getString(lh.getRefreshString(), lh.getStringConstant(R.string.settings_refreshtime_text)));

	}
}
