package com.example.ntp_projekt;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private void createDatabase(){
        String queries = "CREATE TABLE IF NOT EXISTS subject (" +
                "    name   VARCHAR NOT NULL," +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    hmm    INTEGER NOT NULL"+
                ");"+
                "CREATE TABLE IF NOT EXISTS student (" +
                "    id           INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    index_id     INTEGER NOT NULL," +
                "    card_id      VARCHAR NOT NULL" +
                ");" +
                "CREATE TABLE IF NOT EXISTS presence (" +
                "    id           INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    datee       VARCHAR NOT NULL," +
                "    student_id   INTEGER," +
                "    subject_id   INTEGER," +
                "    FOREIGN KEY(student_id) REFERENCES student(id),"+
                "    FOREIGN KEY(subject_id) REFERENCES subject(id)"+
                ");" +
                "CREATE TABLE IF NOT EXISTS relation_3 (" +
                "    pk           INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "    subject_id   INTEGER NOT NULL," +
                "    student_id   INTEGER NOT NULL," +
                "    FOREIGN KEY(student_id) REFERENCES student(id),"+
                "    FOREIGN KEY(subject_id) REFERENCES subject(id)"+
                ")";
        String[] querytab = queries.split(";");
        db = openOrCreateDatabase("Base",MODE_PRIVATE,null);
        for(int i=0;i<querytab.length;i++){
            db.execSQL(querytab[i]+";");
        }

    }
    public void dropTables(){
        db.execSQL("DROP TABLE presence;");
        db.execSQL("DROP TABLE subject;");
        db.execSQL("DROP TABLE student;");
        db.execSQL("DROP TABLE relation_3;");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //dropTables();
        createDatabase();
        setContentView(R.layout.activity_main);

        Cursor c =db.rawQuery("select * from presence;",null);
        c.moveToFirst();
        String s = "";
        do{
            s+=c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3)+"\n";
        }while(c.moveToNext());
        TextView t = findViewById(R.id.textView6);
        t.setText(s);

    }
    public void presenceChecking(View view){
        Intent i = new Intent(this,checkingActivity.class);
        startActivity(i);
    }
    public void addStudent(View view){
        startActivity(new Intent(this, addingStudentActivity.class));
    }
    public void list(View view){
        startActivity(new Intent(this,list.class));
    }
    public void addGroup(View view){ startActivity(new Intent(this,addGoupActivity.class));}
}