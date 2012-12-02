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
}
