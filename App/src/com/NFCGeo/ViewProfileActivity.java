package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class ViewProfileActivity extends Activity
{
    private TextView usernameBox;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        usernameBox = (TextView) findViewById(R.id.userName);
        
        if(MainMenu.user.type() == UserType.USER)
        {
            usernameBox.setText(MainMenu.user.name() + "'s Profile");
        }
        else if(MainMenu.user.type() == UserType.MODERATOR)
        {
            usernameBox.setText(MainMenu.user.name() + "'s Profile ---- MODERATOR");
        }
        else
        {
            usernameBox.setText(MainMenu.user.name() + "'s Profile ---- ADMIN");
        }
    }
}
