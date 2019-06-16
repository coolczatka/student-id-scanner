package com.example.ntp_projekt;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class checkingActivity extends BaseActivity {

    private NfcAdapter nfcAdapter;
    PendingIntent mPendingIntent;
    TextView textViewInfo;
    NfcTagParser p;
    RecyclerView view;
    Boolean group_chosen;
    LinearLayout lay;
    Boolean flag = false;
    GroupList RVadapter; //adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        group_chosen=false;
        lay = findViewById(R.id.checkLayout);
        textViewInfo = findViewById(R.id.info);
        p = new NfcTagParser(nfcAdapter);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        view = findViewById(R.id.recyclerView2);
        Cursor c = db.rawQuery("Select name from subject;",null);
        c.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        do{
            names.add(c.getString(0));
        }while(c.moveToNext());
        RVadapter = new GroupList(this,new ArrayList<Integer>(),new ArrayList<String>(),names);
        view.setAdapter(RVadapter);
        view.setLayoutManager(new LinearLayoutManager(this));
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
        if(group_chosen) {
            resolveIntent(intent);
        }
    }
    private void resolveIntent(Intent intent){
        try{
        String tag = p.getTag(intent);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = dateFormat.format(currentDate);
        if(!db.isOpen())
           db = openOrCreateDatabase("Base",MODE_PRIVATE,null);
        Cursor c;
        c = db.rawQuery("select id from student where card_id='" + tag + "';", null);
        c.moveToFirst();
        int id = c.getInt(0);
        c = db.rawQuery("select id from subject where name='" + RVadapter.getName() + "';", null);
        c.moveToFirst();
        String sub_id = c.getString(c.getColumnIndex("id"));
        db.execSQL("insert into presence(datee,student_id,subject_id)values('" + dateString + "'," + id + "," + sub_id + ");");
        lay.setBackgroundColor(Color.GREEN);
        textViewInfo.setText("jest git jest bit");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lay.setBackgroundColor(Color.TRANSPARENT);
                textViewInfo.setText("");
            }
        }, 500);}
        catch(Exception e){
            textViewInfo.setText(e.getMessage());
        }

    }
    public void button_clicked(View v){
        if(RVadapter.getName()!=""){
            group_chosen = true;
            view.setVisibility(View.GONE);
            Button btn = findViewById(R.id.button7);
            btn.setVisibility(View.GONE);
            textViewInfo.setGravity(Gravity.CENTER);
            Cursor c =db.rawQuery("Select hmm from subject where name='"+RVadapter.getName()+"';",null);
            c.moveToFirst();
            db.execSQL("UPDATE subject set hmm ="+c.getString(0)+"+1 where name='"+RVadapter.getName()+"';");

        }
    }

}
