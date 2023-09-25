package com.example.zikercounter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

public class dashboard extends AppCompatActivity {
 CardView cardView1,cardView4,cardView3,cardView2;
 ImageButton adbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        cardView1 =findViewById(R.id.card1);
        cardView4 =findViewById(R.id.card4);
        cardView3 =findViewById(R.id.card3);
        cardView2 =findViewById(R.id.card2);

//notes
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(dashboard.this, Notes.class);

                startActivity(intent);

            }
        });




        //kids
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(dashboard.this, activity_kids_menue.class);

                startActivity(intent);


            }
        });





// wazaif section

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(dashboard.this, Wazaif_Menue.class);

                startActivity(intent);

            }
        });













        //suggestions
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String subject = "My Suggestions"; // Set your email subject here
                Intent intentMail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "appdreamerz@gmail.com", null));
                intentMail.putExtra(Intent.EXTRA_SUBJECT, subject);


                String suggestions="Here are my suggestions";
                intentMail.putExtra(Intent.EXTRA_TEXT, suggestions);
                startActivity(Intent.createChooser(intentMail, "Choose an email app"));
            }
        });




    }
}