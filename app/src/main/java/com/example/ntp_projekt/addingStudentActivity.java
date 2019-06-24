package com.example.ntp_projekt;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class addingStudentActivity extends BaseActivity {

    private NfcAdapter nfcAdapter;
    private String index_nr;
    private String tag;
    PendingIntent mPendingIntent;
    TextView info;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_student);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        tag = "";
        index_nr = "";
        info = findViewById(R.id.textView4);
        error = findViewById(R.id.textView2);
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }
    public void buttonClicked(View view) {
        EditText t = (EditText)findViewById(R.id.editText2);
        index_nr = t.getText().toString();
        if(validateIndex(index_nr)) {
            if (validateTag(tag)) {
                try {
                    db.execSQL("INSERT INTO student(index_id,card_id) VALUES('" + index_nr + "','" + tag + "');");
                    info.setText("Student dodany");
                }catch(Exception e){
                    error.setText(e.getMessage());
                }
            } else {
                error.setText("Tej legitymacji już użyto");
            }
        }
        else {
            error.setText("Taki indeks już istnieje");
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
        Cursor c = db.rawQuery("Select count(*) from student where index_id="+s+";",null);
        c.moveToFirst();
        if(c.getInt(0)>0){
            result=false;
        }
        return result;

    }
    private boolean validateTag(String s){
        String[] a = s.split(" ");
        if(a.equals(""))
            return false;
        Cursor c = db.rawQuery("Select count(*) from student where card_id='"+s+"';",null);
        c.moveToFirst();
        if(c.getInt(0)>0){
            return false;
        }
        return true;
    }
    @Override
    public void onNewIntent(Intent intent){
        setIntent(intent);
        NfcTagParser p = new NfcTagParser(nfcAdapter);
        tag = p.getTag(intent);
        info.setText("Tag wczytany");
        info.setTextColor(Color.parseColor("#00AA00"));

}
}
