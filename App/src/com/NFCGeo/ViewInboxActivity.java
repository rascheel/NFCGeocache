package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewInboxActivity extends Activity
{
    public final static String MESSAGE_SENDER = "com.NFCGeo.messageSender";
    public final static String MESSAGE_RECIPIENT = "com.NFCGeo.messageRecipient";
    public final static String MESSAGE_SUBJECT = "com.NFCGeo.messageSubject";
    public final static String MESSAGE_BODY = "com.NFCGeo.messageBody";
    public ArrayAdapter<Message> adapter;
    public static Messaging inbox; 

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inbox);
        ListView listView = (ListView) findViewById(R.id.inboxList);
        /*
        Message[] testMessages = new Message[10];
        for(int i = 0; i < 10; i++)
        {
            testMessages[i] = new Message();
            testMessages[i].setSender("Ryan");
            testMessages[i].setRecipient("Santa");
            testMessages[i].setSubject(i + ": Test Subject");
            testMessages[i].setBody("Test Body");
        }*/

        inbox = new Messaging();

        Message[] messages = inbox.retrieveMessages(MainMenu.dbHandle, "Ryan");

        adapter = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1, android.R.id.text1, messages);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnMessageClickListener(this));
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, SendMessageActivity.class);
        startActivity(intent);
    }
}
