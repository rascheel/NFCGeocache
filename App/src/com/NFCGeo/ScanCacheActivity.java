package com.NFCGeo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

@TargetApi(10)
public class ScanCacheActivity extends Activity {
	
	TextView nTextView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.activity_found_cache);

		nTextView = (TextView)findViewById(R.id.found_cache_text_view);
		
		// Check to see if NFC Tag was scanned to start this activity
		Intent intent = getIntent();
		if (intent.getType() != null && intent.getType().equals(NFCGeoMimeType.NFCGEO_MIMETYPE))
		{
			Tag myTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			byte[] tagId = myTag.getId();
			
			Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msg = (NdefMessage) rawMsgs[0];

			//displayMessage(new String(msg.getRecords()[0].getPayload()));
			displayMessage("Cache Found! ID: " + MainMenu.ByteArrayToHexString(tagId) +
					" Name: " + msg);
	    }
				
        
	}

	
	/**
	 * Displays the given String on the screen.
	 * 
	 * @param message
	 *            the String to display
	 */
	private void displayMessage(String message) {
		nTextView.setText(message);
	}


}

