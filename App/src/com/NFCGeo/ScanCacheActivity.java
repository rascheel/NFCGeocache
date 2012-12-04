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
	public final static String CACHE_NAME = "com.NFCGeo.CACHENAME";
	public final static String CACHE_ID = "com.NFCGeo.CACHEID";
	public final static String CACHE_LOCATION = "com.NFCGeo.CACHELOCATION";
	public final static String CACHE_CREATOR = "com.NFCGeo.CACHECREATOR";
	public final static String CACHE_FOUND = "com.NFCGeo.CACHEFOUND";

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		
		//setContentView(R.layout.activity_found_cache);
		//nTextView = (TextView)findViewById(R.id.found_cache_text_view);
		
		// Check to see if NFC Tag was scanned to start this activity
		Intent intent = getIntent();
		if (intent.getType() != null && intent.getType().equals(NFCGeoMimeType.NFCGEO_MIMETYPE))
		{
			Tag myTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			byte[] tagId = myTag.getId();
			
			Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msg = (NdefMessage) rawMsgs[0];

            NdefRecord cacheNameRecord = msg.getRecords()[0];
            NdefRecord cacheLocationRecord = msg.getRecords()[1];
            NdefRecord cacheUserRecord = msg.getRecords()[2];
            
            String cacheName = new String(cacheNameRecord.getPayload());
            String locationString = new String(cacheLocationRecord.getPayload());
            String userString = new String(cacheUserRecord.getPayload());
            
            // Create Intent to use to start the ViewPoint Activity that shows the Cache Info page
            Intent newIntent = new Intent(this, ViewPointActivity.class);
            
            // Store Cache name and ID in the Intent
            newIntent.putExtra(CACHE_ID, MainMenu.ByteArrayToHexString(tagId));
            newIntent.putExtra(CACHE_NAME, cacheName);
            newIntent.putExtra(CACHE_LOCATION, locationString);
            newIntent.putExtra(CACHE_CREATOR, userString);
            newIntent.putExtra(CACHE_FOUND, true);

            startActivity(newIntent);
            
            
//			displayMessage("Cache Found! ID: " + MainMenu.ByteArrayToHexString(tagId) +
//					" Name: " + cacheName);
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

