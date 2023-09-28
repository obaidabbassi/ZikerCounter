package com.example.zikercounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_kids_menue extends AppCompatActivity {
AppCompatButton kids_duas_Btn,daroodBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_menue);
        kids_duas_Btn=findViewById(R.id.kids_duas_Btn);
        daroodBtn=findViewById(R.id.daroodBtn);



        kids_duas_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(activity_kids_menue.this, kids_duas.class);

                startActivity(intent);
            }
        });



        daroodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(activity_kids_menue.this,activity_manzil.class);

                startActivity(intent);

            }
        });

    }
}