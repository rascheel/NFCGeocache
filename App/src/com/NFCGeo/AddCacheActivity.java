package com.NFCGeo;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.sql.SQLException;

import com.google.android.maps.GeoPoint;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import android.widget.EditText;
import android.widget.TextView;

@TargetApi(14)
public class AddCacheActivity extends Activity implements OnClickListener, LocationListener {

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
		
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Location loc = null;
        final boolean gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        int lattitude = 0;
        int longitude = 0;

		// Get current user name
		String username = "";
		
		// Output string 
		String result = "";
		
		// If we can write to the NFC tag, create a new Cache
		if (writeModeEnabled) {
			// Switch from write-enabled mode
			writeModeEnabled = false;

			// Create a new Tag to write to the NFC Tag
			Tag newTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			
			byte[] tagID = newTag.getId();
			newCacheIdString =  MainMenu.ByteArrayToHexString(tagID);
			
			// Get name of cache
			EditText editText = (EditText) findViewById(R.id.cache_name_edit_message);
			String cacheName = editText.getText().toString();
			

	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setPositiveButton("OK", null);
			
	        
			// Get current location
	        
	        if (!gpsEnabled)
	        {
	        	builder.setMessage("GPS is not enabled.");
	        	builder.show();
	        }
	        else
	        {
	        	lm.requestSingleUpdate(new Criteria(), this, null);
	        	loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	        	
	        	if (loc != null)
	            {
	            	lattitude = (int)(1000000*loc.getLatitude());
	            	longitude =  (int)(1000000*loc.getLongitude());
	            	
	            	// Create new Cache 
					Cache newCache = new Cache(newCacheIdString, cacheName,
							lattitude, longitude, username);
					
					addCache(newCache, result);
	            }
	        	else
	        	{
	        		builder.setMessage("Unable to determine location.");
		        	builder.show();
	        	}
	        }				
	        
			// Try to write the NFC tag, if successful add Cache to database
			if (writeTag(newTag, cacheName, lattitude, longitude))
			{
				// Message to output
				result = "Tag written successfully. ID: " + newCacheIdString +
						" Name: " + cacheName;

				
			}
		}
	}

	private void addCache(Cache c, String toDisplay)
	{
		
		
		if (MainMenu.dbAvailable)
		{
			// Attempt to add new Cache to database
			try {
				Caching.Add(c);
			} catch (SQLException e) {

				// Cache not successfully added to database
				// For now, just alert the user that it did not work.
				toDisplay += " ** Database communication error. Unable to add Cache. :-(";
			}
		}
		else
		{
			toDisplay += " ** Database communication error. Unable to add Cache. :-(";
		}
		
						
		displayMessage(toDisplay);
		
		
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
	private boolean writeTag(Tag t, String cacheName, int locLat, int locLong) {
		
		NdefMessage nMessage;
		
		// The first part of the NFC data record will go to the Play Store if
		// this application
		// is not already installed
		NdefRecord playStoreLink = NdefRecord
				.createApplicationRecord("com.NFCGeo");

		String location = locLat + "," + locLong;
		
		// Actual data to write to the Cache
		byte[] cachePayload = (cacheName).getBytes();		
		byte[] locationBytes = (location).getBytes();

		
		// Create byte array that stores our MIME type formatted as ASCII text
		byte[] mimeTypeBytes = NFCGeoMimeType.NFCGEO_MIMETYPE.getBytes(Charset
				.forName("US-ASCII"));
		
		// Create new NDEF data for our application's data
		NdefRecord cacheNameRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeTypeBytes, new byte[0],
				cachePayload);
		NdefRecord locationRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeTypeBytes, new byte[0],
				locationBytes);	
		
		nMessage = new NdefMessage(new NdefRecord[] {cacheNameRecord, locationRecord, playStoreLink} );

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



	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}



	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}



	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}



	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
