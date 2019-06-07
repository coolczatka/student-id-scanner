package com.example.ntp_projekt;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class addingStudentActivity extends AppCompatActivity {

    private boolean isTagPicked;
    private NfcAdapter nfcAdapter;
    private SQLiteDatabase db;
    private String index_nr;
    private String tag;
    PendingIntent mPendingIntent;
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_student);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        isTagPicked = false;
        tag = "";
        index_nr = "";
        info = findViewById(R.id.textView4);
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }
    public void buttonClicked(View view) {
        EditText t = findViewById(R.id.editText2);
        index_nr = (String)t.getText().toString();
        if(validateIndex(index_nr)){
            if(validateTag(tag)){
                db.execSQL("INSERT INTO students VALUES("+index_nr+","+tag+");");
            }
        }

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
    private boolean validateIndex(String s){
        boolean result = true;
        if(s.length()!=6)
            result=false;
        return result;

    }
    private boolean validateTag(String s){
        String[] a = s.split(" ");
        for(int i=0;i<a.length;i++){
            if(a[i].length()>2)
                return false;
        }
        return true;
    }
    @Override
    public void onNewIntent(Intent intent){
        setIntent(intent);
        openOrCreateDatabase("db",MODE_PRIVATE,null);
        NfcTagParser p = new NfcTagParser(nfcAdapter);
        tag = p.getTag(intent);
        info.setText("Tag wczytany");
        info.setTextColor(Color.parseColor("#00AA00"));

}
}
