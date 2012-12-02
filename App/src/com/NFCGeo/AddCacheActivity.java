package com.NFCGeo;

import java.io.IOException;
import java.nio.charset.Charset;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@TargetApi(14)
public class AddCacheActivity extends Activity implements OnClickListener {

	private NfcAdapter nAdapter; /* <NFC Adapter * */
	private TextView nTextView; /* < TextView used for output * */
	private Button nWriteButton;
		
	private String newCacheIdString;
	
	private boolean writeModeEnabled; /* <Write mode flag * */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		nAdapter = NfcAdapter.getDefaultAdapter(this);
		
		setContentView(R.layout.activity_add_cache);	
		nWriteButton = (Button)findViewById(R.id.add_cache_button);
		nWriteButton.setOnClickListener(this);
		
		// Set TextView to output messages
		nTextView = (TextView)findViewById(R.id.add_cache_text_view);
		
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onNewIntent(Intent intent) {
		
		
		// If we can write to the NFC tag, create a new Cache
		if (writeModeEnabled) {
			// Switch from write-enabled mode
			writeModeEnabled = false;

			// Create a new Tag to write to the NFC Tag
			Tag newCache = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			
			byte[] tagID = newCache.getId();
			newCacheIdString =  MainMenu.ByteArrayToHexString(tagID);
			
			writeTag(newCache);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		disableWriteMode();
	}
	
	/**
	 * Writes an NFC tag with Cache data.
	 * 
	 * @param t
	 *            Tag object containing information to write to the NFC tag.
	 * @return true if tag was successfully written, false otherwise
	 */
	private boolean writeTag(Tag t) {
		
		NdefMessage nMessage;
		
		// The first part of the NFC data record will go to the Play Store if
		// this application
		// is not already installed
		NdefRecord playStoreLink = NdefRecord
				.createApplicationRecord("com.NFCGeo");

		// Actual data to write to the Cache
		byte[] cachePayload = (new String("First Tag")).getBytes();		
		
		
		// Create byte array that stores our MIME type formatted as ASCII text
		byte[] mimeTypeBytes = NFCGeoMimeType.NFCGEO_MIMETYPE.getBytes(Charset
				.forName("US-ASCII"));
		
		// Create new NDEF data for our application's data
		NdefRecord appRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeTypeBytes, new byte[0], cachePayload);
					
		
		nMessage = new NdefMessage(new NdefRecord[] {appRecord, playStoreLink} );

		try {
			// Check for NDEF-style format first

			Ndef ndef = Ndef.get(t);

			if (ndef != null) {
				// Open communication with the NFC tag
				ndef.connect();

				// Check if NFC Tag is write-able
				if (!ndef.isWritable()) {
					displayMessage("This tag is read-only.");
					return false;
				}
				
				ndef.writeNdefMessage(nMessage);
				// Success!
				displayMessage("Tag written successfully.Cache ID: " + newCacheIdString);
				return true;

				// Try to write the data to the NFC Tag
			} else {
				// Get ready to format the data we wish to write to the NFC tag
				NdefFormatable nFormat = NdefFormatable.get(t);
				try {
					// Open communication with the NFC tag
					nFormat.connect();
					nFormat.format(nMessage);
					
					// Success!			
										
					displayMessage("Tag written successfully.Cache ID: " + newCacheIdString);
					return true;

				} catch (IOException e) { // If NDEF formatting failed
					displayMessage("NDEF Formatting failed.");
					return false;
				}
			}

		} catch (Exception e) // Could not write NFC tag correctly
		{
			displayMessage("Failed to write NFC Tag.");
			return false;
		}

	}

	private void enableWriteMode() {
		writeModeEnabled = true;

		// Create a pending Intent to launch this Activity when a tag is scanned
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, getClass())
						.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		// New Intent filter to detect when NFC tag is scanned
		IntentFilter tagScanned = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED);
		// Must use an array of Filters to determine which Intent filters to
		// look for
		IntentFilter[] filters = new IntentFilter[] { tagScanned };

		// Give this Activity priority when dispatching the scanned NFC tag
		nAdapter.enableForegroundDispatch(this, pendingIntent, filters, null);
	}
	
	private void disableWriteMode() {
		nAdapter.disableForegroundDispatch(this);
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

	public void onClick(View v) 
	{
		nTextView = (TextView)findViewById(R.id.add_cache_text_view);
		if (v.getId() == R.id.add_cache_button)
		{
			displayMessage("Hold phone against an NFC Tag for a few seconds.");
			enableWriteMode();
		}
		
	}
}
