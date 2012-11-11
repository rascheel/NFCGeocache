package com.NFCGeo;
 
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast; 
/**
 *  Application Name: Generic Login Screen for the Android Platform (back end)
 *  Description: This is a generic login screen which catches the username and password values
 *  Created on: November 23, 2007
 *  Created by: Pogz Ortile
 *  Contact: pogz(at)redhat(dot)polarhome(dot)com
 *  Notes: The string values for username and password are assigned to sUserName and sPassword respectively
 *              You are free to distribute, modify, and wreck for all I care. GPL ya!
 * */
 
public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        // load up the layout
        setContentView(R.layout.activity_login);
       
    }
    
    public void login(View viewParam)
    {
        // this gets the resources in the xml file and assigns it to a local variable of type EditText
        EditText usernameEditText = (EditText) findViewById(R.id.txt_username);
        EditText passwordEditText = (EditText) findViewById(R.id.txt_password);
       
        // the getText() gets the current value of the text box
        // the toString() converts the value to String data type
        // then assigns it to a variable of type String
        String sUserName = usernameEditText.getText().toString();
        String sPassword = passwordEditText.getText().toString();
        
        CharSequence text = "Username: " + sUserName + "\nPassword: " + sPassword; 
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();       
    }
}
