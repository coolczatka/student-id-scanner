package com.example.ntp_projekt;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends BaseActivity {

    TextView t;
    ArrayList<String> groups = new ArrayList<String>();
    ArrayList<Integer> pct = new ArrayList<Integer>();
    ArrayList<String> percentages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init_arrays();
        RecyclerView r = findViewById(R.id.reclay);
        RecyclerViewAdapter a = new RecyclerViewAdapter(this, pct, percentages, groups);
        r.setAdapter(a);
        r.setLayoutManager(new LinearLayoutManager(this));

  /*      t = (TextView)findViewById(R.id.textView);
        try {
            Cursor c = db.rawQuery("SELECT index_id from student;", null);
            c.moveToFirst();
            String result = "";
            do {
                String index = c.getString(c.getColumnIndex("index_id"));
                result += index + "\n";
            } while (c.moveToNext());
            t.setText(result);

        }catch(Exception e){
            t.setText(e.getMessage());
        }

    */
    }
    private void init_arrays(){
        String s= "";
        try{
            Cursor c =db.rawQuery("SELECT * from subject;",null);
            c.moveToFirst();

            do{
                Double percent;
                String st;
                Integer per;
                try {
                    groups.add(c.getString(c.getColumnIndex("name")));
                    //ile razy ktoś przyszedł
                    Cursor d = db.rawQuery("Select count(*) from presence where subject_id=" + c.getString(c.getColumnIndex("id")) + ";", null);
                    Cursor e = db.rawQuery("Select hmm from subject where id=" + c.getString(c.getColumnIndex("id")) + ";", null);
                    Cursor f = db.rawQuery("Select count(*) from relation_3 where subject_id=" + c.getString(c.getColumnIndex("id")) + ";", null);
                    d.moveToFirst();
                    e.moveToFirst();
                    f.moveToFirst();
                    percent = (double) (1.0*d.getInt(0) / (e.getInt(0) * f.getInt(0)));
                    per = (int)(percent*100);
                    st = per.toString();
                }catch(ArithmeticException ex){
                    per = 0;
                    st = "BZ";

                }
                pct.add(per);
                percentages.add(st);

            }while (c.moveToNext());
        }catch(Exception e){
            t.setText(e.getMessage()+s);
        }
    }
}
