package com.example.homework8;

import static android.provider.Telephony.Mms.Part.FILENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView tV;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tV = findViewById(R.id.textView);
        ed = findViewById(R.id.editTextText);
        tV.setText(read());
    }

    public void value(boolean val){
        try{
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            if (val){
                bW.write("");
            }
            else{
                bW.write(tV.getText()+" "+ed.getText().toString());
            }
            bW.close();
            oSW.close();
            fOS.close();
        }
        catch(Exception e){
            Toast.makeText(this, "Error, try again", Toast.LENGTH_SHORT).show();
        }
    }

    public String read(){
        try{
            FileInputStream fIS= openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line);
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            fIS.close();
            return sB.toString();}
        catch(Exception e){
            Toast.makeText(this, "Error try again", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void Save(View view) {
        value(false);
        tV.setText(read());
    }

    public void Reset(View view) {
        value(true);
        tV.setText("");
    }

    public void Exit(View view) {
        finish();
    }
}