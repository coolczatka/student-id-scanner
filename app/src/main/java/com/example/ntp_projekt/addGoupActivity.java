package com.example.ntp_projekt;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class addGoupActivity extends BaseActivity {

    EditText edit;
    TextView error;
    ArrayList<String> index_nrs = new ArrayList<String>();
    LinkedList<String> list = new LinkedList<String>();
    RecyclerView view;
    RecyclerViewAdapterSt adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goup);
        edit = findViewById(R.id.editText);
        error = findViewById(R.id.textView10);
        view = findViewById(R.id.recyclerView);
        Cursor c = db.rawQuery("Select id from student;", null);
        c.moveToFirst();
        do {
            index_nrs.add(c.getString(0));
        } while (c.moveToNext());
        adapter = new RecyclerViewAdapterSt(index_nrs);
        view.setAdapter(adapter);
    }

    public void addGroup(View view) {

        Cursor c;
        Boolean ok = true;
        String count = "SELECT count(*) FROM subject;";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0) {
            c = db.rawQuery("Select name from subject;", null);
                c.moveToFirst();
                error.setText(edit.getText().toString());
                do {
                    if (edit.getText().toString() == c.getString(0)) {
                        error.setText("Taka nazwa już istnieje");
                        ok = false;
                    }
                } while (c.moveToNext());
        }
        if (ok) {
            db.execSQL("Insert into subject(name,hmm) values('" + edit.getText() + "',0);");
        } else
            return;
        list = adapter.getChosen_nrs();
        c = db.rawQuery("select id from subject where name='" + edit.getText() + "';", null);
        c.moveToFirst();
        Integer id = c.getInt(0);
        String s = "";
        error.setText(String.valueOf(list.size()));
        for (int i = 0; i < list.size(); i++) {
           db.execSQL("insert into relation_3(subject_id,student_id)values(" + id + "," + list.get(i) + ");");
        }


    }
}
