package com.example.ntp_projekt;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    private void createDatabase(){
        String queries = ("CREATE TABLE IF NOT EXISTS presences (\n" +
                "    id           INTEGER NOT NULL,\n" +
                "    \"date\"       VARCHAR2(10) NOT NULL,\n" +
                "    student_id   INTEGER,\n" +
                "    subject_id   INTEGER\n" +
                ");\n" +
                "\n" +
                "ALTER TABLE presences ADD CONSTRAINT presences_pk PRIMARY KEY ( id );\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS relation_3 (\n" +
                "    subject_id   INTEGER NOT NULL,\n" +
                "    student_id   INTEGER NOT NULL\n" +
                ");\n" +
                "\n" +
                "ALTER TABLE relation_3 ADD CONSTRAINT relation_3_pk PRIMARY KEY ( subject_id,\n" +
                "                                                                  student_id );\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS student (\n" +
                "    id           INTEGER NOT NULL,\n" +
                "    index_id     INTEGER NOT NULL,\n" +
                "    card_id      VARCHAR2(6) NOT NULL,\n" +
                "    subject_id   INTEGER\n" +
                ");\n" +
                "\n" +
                "ALTER TABLE student ADD CONSTRAINT student_pk PRIMARY KEY ( id );\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS subject (\n" +
                "    name   VARCHAR2(50) NOT NULL,\n" +
                "    id     INTEGER NOT NULL\n" +
                ");\n" +
                "\n" +
                "ALTER TABLE subject ADD CONSTRAINT subject_pk PRIMARY KEY ( id );\n" +
                "\n" +
                "ALTER TABLE presences\n" +
                "    ADD CONSTRAINT presences_student_fk FOREIGN KEY ( student_id )\n" +
                "        REFERENCES student ( id );\n" +
                "\n" +
                "ALTER TABLE presences\n" +
                "    ADD CONSTRAINT presences_subject_fk FOREIGN KEY ( subject_id )\n" +
                "        REFERENCES subject ( id );\n" +
                "\n" +
                "ALTER TABLE relation_3\n" +
                "    ADD CONSTRAINT relation_3_student_fk FOREIGN KEY ( student_id )\n" +
                "        REFERENCES student ( id );\n" +
                "\n" +
                "ALTER TABLE relation_3\n" +
                "    ADD CONSTRAINT relation_3_subject_fk FOREIGN KEY ( subject_id )\n" +
                "        REFERENCES subject ( id );\n" +
                "\n" +
                "ALTER TABLE student\n" +
                "    ADD CONSTRAINT student_subject_fk FOREIGN KEY ( subject_id )\n" +
                "        REFERENCES subject ( id );\n");
        String[] querytab = queries.split(";");
        openOrCreateDatabase("db",MODE_PRIVATE,null);
        for(int i=0;i<querytab.length;i++){
            db.execSQL(querytab[i]+";");
        }
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