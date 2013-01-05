package scanner.scanner;

import java.util.ArrayList;

import scanner.exception.CouldNotReadDatabaseException;
import scanner.persistance.DatabaseConnection;
import scanner.scannerListener.SaveListener;
import scanner.scannerListener.ScanListener;
import scanner.scannerListener.SynchListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * AndroidScannerActivity
 * 
 * @author Stefan Braun
 * 
 */

public class AndroidScannerActivity extends Activity {
	private Button scanButton;
	private EditText eanNumberText;
	private Button closeButton;
	private Button saveEanButton;
	private Button synchButton;
	private ListView eanView;
	private ArrayList<String> listElements;
	private DatabaseConnection dbc;
	private SaveListener saveListener;
	private SynchListener synchListener;
	private ScanListener scanListener;
	private ArrayAdapter<String> listViewAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.initComponents();

	}

	private void initComponents() {

		/* DATENBANK VERBINDUNG */
		this.dbc = DatabaseConnection.getConnection(this);

		/* SAVE BUTTON */
		this.saveListener = new SaveListener("", this.dbc, this);
		this.saveEanButton = (Button) this.findViewById(R.id.saveEanButton);
		this.saveEanButton.setOnClickListener(this.saveListener);

		/* SYNCH BUTTON */
		this.synchListener = new SynchListener(this);
		this.synchButton = (Button) this.findViewById(R.id.synchButton);
		this.synchButton.setOnClickListener(this.synchListener);

		/* SCAN BUTTON */
		this.scanListener = new ScanListener(this);
		this.scanButton = (Button) this.findViewById(R.id.scanButton);
		this.scanButton.setOnClickListener(this.scanListener);

		/* CLOSE BUTTON */
		this.closeButton = (Button) this.findViewById(R.id.closeButton);
		this.closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		/* EAN TEXTFELD */
		this.eanNumberText = (EditText) this.findViewById(R.id.eanTextEdit);

		/* ListView */
		this.eanView = (ListView) this.findViewById(R.id.eanNumberList);
		this.UpdateEanList();

	}

	public void UpdateEanList() {
		try {
			this.listElements = dbc.getEanNumbersBySynchronizeStat("F");
		} catch (CouldNotReadDatabaseException e) {
			Log.d("Database", "getEanNumbersBySynchronizeStat");
		}
		if (this.listElements != null) {
			if (this.listElements.size() >= 0) {
				try {
					this.listViewAdapter = new ArrayAdapter<String>(this, R.layout.list_item, this.listElements);
					this.eanView.setAdapter(listViewAdapter);
					this.listViewAdapter.notifyDataSetChanged();
				} catch (NullPointerException e) {
					Log.d("ListView", "Es liegen keine Codes mit isSynchronized = F vor.");
				}
			}
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {

				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

				this.eanNumberText.setText(contents);
				if (!this.saveListener.isUserAndConnectionSet()) {
					this.saveListener.setDBC(this.dbc);
					// TODO: user id is hardcoded
					this.saveListener.setUserId(1);

				}
				this.saveListener.setEanCode(contents);
				this.UpdateEanList();
				Log.d("saveListener", "Ean-Code updated.");

				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

}