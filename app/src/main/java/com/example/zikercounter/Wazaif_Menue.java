package com.example.zikercounter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Wazaif_Menue extends AppCompatActivity {
    private AdView mAdView;
    AppCompatButton btnwazaif,wazaifcardother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wazaif_menue);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//ads section
        try {
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    Log.d("AD_TEST", "Ad loaded successfully");
                }

            });
        } catch (Exception e) {
            Log.d("AD_TEST", "Exception while loading ad: " + e.getMessage());
        }


        btnwazaif=findViewById(R.id.btnwaziaf);
        wazaifcardother=findViewById(R.id.wazaifcardother);

wazaifcardother.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(Wazaif_Menue.this, Comingsoon.class);

        startActivity(intent);

    }
});


        btnwazaif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Wazaif_Menue.this, Weekly_wazaif.class);

                startActivity(intent);
            }
        });

































    }
}