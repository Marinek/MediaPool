package scanner.alert;

import scanner.scanner.AndroidScannerActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


/**
 * AndroidScannerAlert to alert and inform the User
 * 
 * @author sfactory
 *
 */
public class AndroidScannerAlert {
	
	private AndroidScannerActivity myActivity;
	private AlertDialog.Builder alertbox;
	private String altertBoxMessage="";
	private String toastMessage="";
	
	
	public AndroidScannerAlert(AndroidScannerActivity myActivity){
		this.myActivity=myActivity;
		this.alertbox = new AlertDialog.Builder(this.myActivity);
	}

	public void alertMessage(final String toastMessageIn){	
		this.alertbox.setMessage(this.altertBoxMessage);
		this.alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

			// click listener on the alert box
			public void onClick(DialogInterface arg0, int arg1) {
				// the button was clicked
				Toast.makeText(myActivity.getApplicationContext(), toastMessageIn, Toast.LENGTH_LONG).show();
			}
		});
		this.alertbox.show();
	}

	public void alertMessage(){	
		this.alertbox.setMessage(this.altertBoxMessage);
		this.alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

			// click listener on the alert box
			public void onClick(DialogInterface arg0, int arg1) {
				// the button was clicked
				Toast.makeText(myActivity.getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
			}
		});
		this.alertbox.show();
	}

	public void alertMessageByString(final String toastMessageIn, final String alertBoxMessageIn){
		this.alertbox.setMessage(alertBoxMessageIn);
		this.alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

			// click listener on the alert box
			public void onClick(DialogInterface arg0, int arg1) {
				// the button was clicked
				Toast.makeText(myActivity.getApplicationContext(), toastMessageIn, Toast.LENGTH_LONG).show();
			}
		});
		this.alertbox.show();
	}
	
	public void alertToast(){
		Toast.makeText(myActivity.getApplicationContext(), this.toastMessage, Toast.LENGTH_LONG).show();
	}

	public void setMessage(String message){
		this.altertBoxMessage=message;
	}
	public void setToastMessage(String message){
		this.toastMessage=message;
	}

}
