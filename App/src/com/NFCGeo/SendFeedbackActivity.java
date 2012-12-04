package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;

public class SendFeedbackActivity extends Activity
{
    public EditText feedbackBox;
    public static Messaging messageHandler;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        feedbackBox = (EditText) findViewById(R.id.feedbackField);
    }

    public void sendFeedback(View view)
    {

        if(MainMenu.dbAvailable)
        {
            Message message = new Message();

            message.setSender(MainMenu.user.name());
            message.setRecipient("ryan");//TODO: set this up to message all the admins
            message.setSubject("USER FEEDBACK");
            message.setBody(feedbackBox.getText().toString());
            
            messageHandler.Send(MainMenu.dbHandle, message);
        }
        else
        {
            MainMenu.noDatabaseConnection(this);
        }


    }
}
