package scanner.scannerListener;


import scanner.alert.AndroidScannerAlert;
import scanner.persistance.DatabaseConnection;
import scanner.scanner.AndroidScannerActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * SaveListener
 * 
 * @author sfactory
 *
 */

public class SaveListener implements OnClickListener {
	private DatabaseConnection dbc = null;
	private String eanCode = "";
	private int UserId =-1;
	private AndroidScannerActivity myActivity;
	private AndroidScannerAlert myAlert;
	
	
	public SaveListener(String eanCode,DatabaseConnection dbc,AndroidScannerActivity myActivity){
		this.eanCode=eanCode;
		this.dbc=dbc;
		this.myActivity=myActivity;
	}
	
	public SaveListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		
		this.myAlert = new AndroidScannerAlert(this.myActivity);
		
		if(!this.eanCode.isEmpty()){
			if(this.UserId != -1){
				if(dbc.isCodExisting(this.eanCode)){
					this.myAlert.setMessage("Code ist schon vorhanden!");
					this.myAlert.setToastMessage("Code wurde nicht gespeichert...");
					this.myAlert.alertMessage();
				}
				else{
					dbc.saveNewEanNumber(this.eanCode, this.UserId);
					this.myActivity.UpdateEanList();
					this.myAlert.setToastMessage("Code wurde gespeichert...");
					this.myAlert.alertToast();
				}
				
			}
			else{
				Log.d("Save", "userid empty");
			}
		}
		else{
			Log.d("Save", "eanCode empty."+this.eanCode);
		}
	}
	public void setUserId(int id){
		this.UserId=id;
	}
	
	public void setDBC(DatabaseConnection dbc){
		this.dbc=dbc;
	}
	public void setEanCode(String eanCode){
		this.eanCode=eanCode;
	}
	public boolean isUserAndConnectionSet(){
		boolean isSet = false;
		if( (this.dbc!=null) & (this.UserId!=-1) ){
			isSet = true;
		}
		return isSet;
	}

}
