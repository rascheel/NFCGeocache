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
	
	TextView mInfoText;
	NfcAdapter mNfcAdapter;
	TextView nTextView;
	PendingIntent pendingIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		
		
		
		// Check to see if NFC Tag was scanned to start this activity
		Intent intent = getIntent();
		//if (intent.hasExtra(NfcAdapter.EXTRA_TAG))
		
		{
			//Tag myTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			//byte[] tagId = myTag.getId();
			
			//Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            //NdefMessage msg = (NdefMessage) rawMsgs[0];

			//displayMessage(new String(msg.getRecords()[0].getPayload()));
			//displayMessage(ByteArrayToHexString(tagId));
	    }
		displayMessage("" + intent.getAction());
		
        
	}


	public void onResume()
	{
		super.onResume();
		setContentView(R.layout.activity_found_cache);
		
        nTextView = (TextView)findViewById(R.id.found_cache_view);
        nTextView.setTextSize(25);
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()))
		{
			displayMessage("Hello");
		}
	}

	@Override
	public void onNewIntent(Intent intent) {
		// onResume gets called after this to handle the intent
		setIntent(intent);
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

	/**
	 * Converts an array of bytes to a hex-formatted String.
	 * @param byteArray		the byte array to convert
	 * @return	the hex format of the byte array as a String
	 */
	public String ByteArrayToHexString(byte[] byteArray) {
		int i, j, in;
		String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F" };
		String hexString = "";

		for (j = 0; j < byteArray.length; ++j) {
			in = (int) byteArray[j] & 0xff;
			i = (in >> 4) & 0x0f;
			hexString += hex[i];
			i = in & 0x0f;
			hexString += hex[i];
		}

		return hexString;
	}
}

