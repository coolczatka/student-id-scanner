package com.example.ntp_projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.nfc.NfcAdapter;
import android.app.PendingIntent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   protected NfcAdapter adapter;
   TextView tv;

   protected void cnstr(){
       this.adapter = NfcAdapter.getDefaultAdapter(this);
       this.tv = findViewById(R.id.textfield);
       if(!this.adapter.isEnabled())
           this.tv.setText("Włącz nfc");
       else
           this.tv.setText("jest wlączone pozdro");
       this.adapter.
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cnstr();
    }
}
