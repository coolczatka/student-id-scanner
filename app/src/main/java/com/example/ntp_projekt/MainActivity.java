package com.example.ntp_projekt;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    private void createDatabase(){
        String query = "CREATE TABLE IF NOT EXISTS presences (" +
                "    id           INTEGER NOT NULL," +
                "    \"date\"       VARCHAR2(10) NOT NULL, " +
                "    student_id   INTEGER " +
                ");";
        openOrCreateDatabase("db",MODE_PRIVATE,null);
        db.execSQL(query);
        db.close();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void presenceChecking(View view){
        Intent i = new Intent(this,checkingActivity.class);
        startActivity(i);
    }
    public void addStudent(View view){
        startActivity(new Intent(this, addingStudentActivity.class));
    }
}