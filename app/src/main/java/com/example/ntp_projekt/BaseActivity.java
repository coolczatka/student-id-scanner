package com.example.ntp_projekt;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.logging.Logger;

public class BaseActivity extends AppCompatActivity {
    protected static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle a){
        super.onCreate(a);
        db=openOrCreateDatabase("Base",MODE_PRIVATE,null);
    }
    @Override
    protected void onResume(){
        if(!db.isOpen()) {
            db = openOrCreateDatabase("Base", MODE_PRIVATE, null);
        }
        super.onResume();
    }
    @Override
    protected void onPause(){
        if(db.isOpen()) {
            db.close();
        }
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        if(db.isOpen()) {
            db.close();
        }
        super.onDestroy();
    }
}
