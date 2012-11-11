package com.NFCGeo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
//import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ViewRankingsActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rankings);

        ListView listView = (ListView) findViewById(R.id.rankingsList);

        String[] testRankings = new String[10];
        for(int i = 0; i < 10; i++)
        {
            testRankings[i] = i + ". Ryan";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, testRankings);

        listView.setAdapter(adapter);
    }
}
