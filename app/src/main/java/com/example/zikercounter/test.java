package com.example.zikercounter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import yuku.ambilwarna.AmbilWarnaDialog;

public class test extends AppCompatActivity {
int currentColor=Color.WHITE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        @SuppressLint("MissingInflatedId") LinearLayout layout=findViewById(R.id.linear);

        @SuppressLint("MissingInflatedId") AppCompatButton color =findViewById(R.id.colorr);


        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AmbilWarnaDialog ambilWarnaDialog= new AmbilWarnaDialog(test.this, currentColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {

                        currentColor=color;
//                     layout.setBackgroundColor(Color.rgb(Color.red(color), Color.green(color), Color.blue(color)));

                        layout.setBackgroundColor(color);
                        Log.d("AmbilWarnaDialog", "Selected color: " + color);
                        Log.d("AmbilWarnaDialog", "Background col or set to: " + color);

                    }
                });
                ambilWarnaDialog.show();






            }
        });














    }


}