package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

public class SendMessageActivity extends Activity
{
    private String messageSender;
    private String messageRecipient;
    private String messageSubject;
    private String messageBody;

	private EditText toField;
	private EditText subjectField;
	private EditText bodyField;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Intent intent = getIntent();

        messageSender = "Test Sender";
        messageRecipient = intent.getStringExtra(ReadMessageActivity.MESSAGE_REPLY_RECIPIENT);
        messageSubject = intent.getStringExtra(ReadMessageActivity.MESSAGE_REPLY_SUBJECT);
        messageBody = intent.getStringExtra(ReadMessageActivity.MESSAGE_REPLY_BODY);
	
	toField = (EditText) findViewById(R.id.toField);
	subjectField = (EditText) findViewById(R.id.subjectField);
	bodyField = (EditText) findViewById(R.id.bodyField);

	toField.setText(messageRecipient);
	subjectField.setText(messageSubject);
	bodyField.setText(messageBody);
    }

	public void sendMessage(View view)
	{	
		Message message = new Message();
		message.setSender(messageSender);
		message.setRecipient(toField.getText().toString());
		message.setSubject(subjectField.getText().toString());
		message.setBody(bodyField.getText().toString());

        ViewInboxActivity.inbox.Send(MainMenu.dbHandle, message);
	}
}
