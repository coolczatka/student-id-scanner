package com.example.ntp_projekt;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.nfc.NfcAdapter;

public class NfcTagParser {
    private NfcAdapter nfcAdapter;


    private String mess;

    public String getMess() {
        return mess;
    }

    public NfcTagParser(NfcAdapter b){
        nfcAdapter = b;
        mess="";
    }
    public boolean checkifok(){
        String message = "";
        if(nfcAdapter == null){
            mess= "NFC NOT supported on this devices!";
            return false;
        }else if(!nfcAdapter.isEnabled()){
            mess = "NFC not enabled";
            return false;
        }
        return true;
    }
    public String getTagInfo(Intent intent){
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);

            if(tag == null){
                return "";
            }else{
                String tagInfo = tag.toString() + "\n";

                tagInfo += "\nTag Id: \n";
                tagInfo += "dlugosc = " + tagId.length +" bajty\n";
                for(int i=0; i<tagId.length; i++){
                    tagInfo += Integer.toHexString(tagId[i] & 0xFF) + " ";
                }
                return tagInfo;
            }
        }
        return "";
    }
    public String getTag(Intent intent){
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);

            if(tag == null){
                return "";
            }else{
                String tagS = "";
                for(int i=0; i<tagId.length; i++){
                    tagS += Integer.toHexString(tagId[i] & 0xFF) + " ";
                }
                return tagS;
            }
        }
        return "";
    }
}
