package com.example.ntp_projekt;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentDetailsActivity extends BaseActivity {
    ArrayList<Integer> pct = new ArrayList<Integer>();
    ArrayList<String> percentages = new ArrayList<String>();
    ArrayList<String> groups = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String extra = getIntent().getExtras().getString("group_nr");
        setContentView(R.layout.activity_student_details);
        try {
            Cursor c = db.rawQuery("Select s.id, s.index_id from student s join relation_3 r on s.id=r.student_id join subject su on su.id=r.subject_id " +
                    "where su.name='" + extra + "';", null);
            c.moveToFirst();
            do {
                String query = "select id from subject where name='" + extra+ "'";
                Cursor d = db.rawQuery("Select count(*) from presence where student_id=" + c.getString(c.getColumnIndex("id")) + " and subject_id in (" + query + ");", null);
                Cursor e = db.rawQuery("Select hmm from subject where name='" + extra + "';", null);
                d.moveToFirst();
                e.moveToFirst();
                double perc = 1.0 * d.getInt(0) / e.getInt(0);
                int percent = (int) (perc * 100);
                pct.add(percent);
                percentages.add(String.valueOf(percent));
                groups.add(c.getString(c.getColumnIndex("index_id")));
            } while (c.moveToNext());
            RecyclerView view = findViewById(R.id.recyclerView3);
            StudentList adapter = new StudentList(this, pct, percentages, groups);
            view.setAdapter(adapter);
        }catch(Exception e){
            
        }
    }
}
