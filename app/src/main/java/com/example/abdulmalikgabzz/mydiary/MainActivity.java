package com.example.abdulmalikgabzz.mydiary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {
    private EditText inputEdit;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEdit = (EditText)findViewById(R.id.input);
        btn=(Button)findViewById(R.id.save);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputEdit.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter message", Toast.LENGTH_LONG).show();
                }else{
                    writetofile(inputEdit.getText().toString());
                    Toast.makeText(getApplicationContext(), "Message Saved!", Toast.LENGTH_LONG).show();
                }
            }
        });
        if(readfromfile() != null)
            inputEdit.setText(readfromfile());
    }

    public void writetofile(String mydate){
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("mytot.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydate);
            outputStreamWriter.close();
        }catch (IOException e){
            Log.v("MainActivity","Error: "+e.toString());
        }
    }

    public String readfromfile(){
        String v = "";

        try {
            InputStream inputStream = openFileInput("mytot.txt");
            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String value = "";
                StringBuilder stringBuilder = new StringBuilder();
                while((value = bufferedReader.readLine()) != null){
                    stringBuilder.append(value);
                }
                inputStream.close();
                v = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }
}
