package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;

public class ReadMessageActivity extends Activity
{
    private String messageSender;
    private String messageRecipient;
    private String messageSubject;
    private String messageBody;

    public final static String MESSAGE_REPLY_RECIPIENT = "com.NFCGeo.messageReplyRecipient";
    public final static String MESSAGE_REPLY_SUBJECT = "com.NFCGeo.messageReplySubject";
    public final static String MESSAGE_REPLY_BODY = "com.NFCGeo.messageReplyBody";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);

        Intent intent = getIntent();
        messageSender = intent.getStringExtra(ViewInboxActivity.MESSAGE_SENDER); 
        messageRecipient = intent.getStringExtra(ViewInboxActivity.MESSAGE_RECIPIENT); 
        messageSubject = intent.getStringExtra(ViewInboxActivity.MESSAGE_SUBJECT); 
        messageBody = intent.getStringExtra(ViewInboxActivity.MESSAGE_BODY); 

        TextView fromField = (TextView) findViewById(R.id.fromField);
        TextView subjectField = (TextView) findViewById(R.id.subjectField);
        TextView bodyField = (TextView) findViewById(R.id.bodyField);

        fromField.setText(messageSender);
        subjectField.setText(messageSubject);
        bodyField.setText(messageBody); 
    }

    public void reply(View view)
    {
        

        Intent intent = new Intent(this, SendMessageActivity.class);

        intent.putExtra(MESSAGE_REPLY_RECIPIENT, messageSender);
        intent.putExtra(MESSAGE_REPLY_SUBJECT, "Re: " + messageSubject);

        String replyBody = new String();
        replyBody = "\n================================\n" + messageBody;
        intent.putExtra(MESSAGE_REPLY_BODY, replyBody);

        startActivity(intent);
    }
}
