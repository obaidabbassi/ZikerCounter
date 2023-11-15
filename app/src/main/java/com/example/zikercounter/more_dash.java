package com.example.zikercounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class more_dash extends AppCompatActivity {
CardView manzil_card,yasin_card,waqia_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_dash);

        manzil_card=findViewById(R.id.manzil_card);
        waqia_card=findViewById(R.id.waqia_card);

yasin_card=findViewById(R.id.yasin_card);




        manzil_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),activity_manzil.class);

          startActivity(intent);
            }
        });


       yasin_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),activity_yasin.class);

                startActivity(intent);
            }
        });




     waqia_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),waqia.class);

                startActivity(intent);
            }
        });




    }
}