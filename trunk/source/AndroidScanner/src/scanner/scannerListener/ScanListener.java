package scanner.scannerListener;

import scanner.scanner.AndroidScannerActivity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * ScanListener
 * 
 * @author sfactory
 *
 */
public class ScanListener implements OnClickListener {
	private AndroidScannerActivity myActivity;
	
	public ScanListener(AndroidScannerActivity myActivity){
		this.myActivity=myActivity;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "SCAN_MODE");
		this.myActivity.startActivityForResult(intent, 0);
	}

}
