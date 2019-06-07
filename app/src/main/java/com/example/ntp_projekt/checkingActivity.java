package com.example.ntp_projekt;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class checkingActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    PendingIntent mPendingIntent;
    TextView textViewInfo;
    SQLiteDatabase db;
    NfcTagParser p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        textViewInfo = findViewById(R.id.info);
        p = new NfcTagParser(nfcAdapter);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
    }
    @Override
    protected void onPause(){
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }
    @Override
    public void onNewIntent(Intent intent){
        setIntent(intent);
        resolveIntent(intent);
    }
    private void resolveIntent(Intent intent){
        String tagInfo = p.getTag(intent);
        textViewInfo.setText(tagInfo);

    }

}
