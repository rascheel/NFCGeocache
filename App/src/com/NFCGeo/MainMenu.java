package com.NFCGeo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;

public class MainMenu extends Activity
{
    public static DatabaseHandler dbHandle;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        try
        {
    	dbHandle = new DatabaseHandler("","root","rootpw");
    	dbHandle.openDB();
        }
        catch(Exception e)
        {
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void viewMap(View view) {
    	Intent intent = new Intent(this, ViewMapActivity.class);
    	startActivity(intent);
    }

    public void addCache(View view)
    {
        Intent intent = new Intent(this, AddCacheActivity.class);
        startActivity(intent);
    }

    public void viewProfile(View view)
    {
        Intent intent = new Intent(this, ViewProfileActivity.class);
        startActivity(intent);
    }

    public void viewInbox(View view)
    {
        Intent intent = new Intent(this, ViewInboxActivity.class);
        startActivity(intent);
    }

    public void viewRankings(View view)
    {
        Intent intent = new Intent(this, ViewRankingsActivity.class);
        startActivity(intent);
    }

    public void sendFeedback(View view)
    {
        Intent intent = new Intent(this, SendFeedbackActivity.class);
        startActivity(intent);
    }

   public void loginPage(View view)
   {
       Intent intent = new Intent(this, LoginActivity.class);
       startActivity(intent);
   } 
	/**
	 * Converts an array of bytes to a hex-formatted String.
	 * @param byteArray		the byte array to convert
	 * @return	the hex format of the byte array as a String
	 */
	public static String ByteArrayToHexString(byte[] byteArray) {
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
