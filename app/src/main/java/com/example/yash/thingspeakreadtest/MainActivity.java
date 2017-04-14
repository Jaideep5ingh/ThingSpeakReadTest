package com.example.yash.thingspeakreadtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startService,stopService;
    TextView textView;
    BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.button);
        stopService = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ReadDataService.class);
                startService(i);
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(),ReadDataService.class);
                stopService(p);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(br != null){
            br = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    textView.setText(intent.getExtras().getString("field1"));
                }
            };
            registerReceiver(br,new IntentFilter("location_update"));
        }
    }

}
