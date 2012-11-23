package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnMessageClickListener implements OnItemClickListener
{
    private ViewInboxActivity inboxActivity;

    public OnMessageClickListener(ViewInboxActivity _inboxActivity)
    {
        super();
        inboxActivity = _inboxActivity;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    {
        Intent intent = new Intent(inboxActivity, ReadMessageActivity.class);
        Message messageClicked = inboxActivity.adapter.getItem(position);
        intent.putExtra(inboxActivity.MESSAGE_SENDER, messageClicked.getSender());
        intent.putExtra(inboxActivity.MESSAGE_RECIPIENT, messageClicked.getRecipient());
        intent.putExtra(inboxActivity.MESSAGE_SUBJECT, messageClicked.getSubject());
        intent.putExtra(inboxActivity.MESSAGE_BODY, messageClicked.getBody());

        inboxActivity.startActivity(intent);
    }
}
