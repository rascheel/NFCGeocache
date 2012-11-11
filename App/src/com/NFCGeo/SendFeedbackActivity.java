package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;

public class SendFeedbackActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        EditText feedback = (EditText) findViewById(R.id.feedbackField);

        feedback.setText("Enter Feedback here");
    }

    public void sendFeedback(View view)
    {
        //TODO: send feedback
    }
}
