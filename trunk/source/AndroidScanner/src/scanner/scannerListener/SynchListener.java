package scanner.scannerListener;

import scanner.alert.AndroidScannerAlert;
import scanner.persistance.DatabaseConnection;
import scanner.scanner.AndroidScannerActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * SynchListener
 * 
 * @author sfactory
 *
 */
public class SynchListener implements OnClickListener{
	
	private AndroidScannerActivity myActivity;

	public SynchListener(AndroidScannerActivity myActivity){

		this.myActivity=myActivity;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Hier kommt der Part für die Webservice Synchronisation rein
		
		//testcode
		AndroidScannerAlert myAlert = new AndroidScannerAlert(this.myActivity);
		myAlert.alertMessageByString("es wurde gespeichert", "speichern?");
		
	}

}
