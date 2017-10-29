package com.example.aniketkumar.hawkeye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Friend extends AppCompatActivity {

    EditText name,id;
    // Button save;
    String nam,ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__friend);
        name=(EditText)findViewById(R.id.name);
        id=(EditText)findViewById(R.id.id);


    }
    public void save (View view)
    {
        nam=name.getText().toString();
        ID=id.getText().toString();
        Log.d("TAGG::","name="+name+"id="+id);
        SharedPreferences sharedPreferences= getSharedPreferences("com.example.aniketkumar.suspect_app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("NAME",nam);
        //editor.putString("ID",ID);
        editor.commit();
        String check="user";
        String message;
        SharedPreferences sharedPreference= getSharedPreferences("com.example.aniketkumar.suspect_app", Context.MODE_PRIVATE);
        message=sharedPreference.getString("ID","Default");
        Background_process  background_process=new Background_process(this);
        background_process.execute(message,nam,ID);
        finish();
    }
}
