package com.example.zikercounter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatImageButton;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.messaging.FirebaseMessaging;

import yuku.ambilwarna.AmbilWarnaDialog;


public class MainActivity extends AppCompatActivity {

    TextView tv;
    AppCompatImageButton btn, rebtn,led,color,volume,mute;
    SharedPreferences preferences;
    BottomNavigationView bottomNavigationView;
    private MediaPlayer mediaPlayer1;
    private static final String Count_key = "count";
    int count = 0;
    ImageView imgOff,imageOn,bg;
    private AdView mAdView;
    int currentColor = Color.WHITE;
    boolean chk=false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);






//color chooser section

     color=   findViewById(R.id.colorr);
bg=findViewById(R.id.bg);




        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

AmbilWarnaDialog ambilWarnaDialog= new AmbilWarnaDialog(MainActivity.this, currentColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
    @Override
    public void onCancel(AmbilWarnaDialog dialog) {

    }

    @Override
    public void onOk(AmbilWarnaDialog dialog, int color) {

currentColor=color;
bg.setBackgroundColor(color);


        Log.d("AmbilWarnaDialog", "Selected color: " + color);
        Log.d("AmbilWarnaDialog", "Background col or set to: " + color);

    }
});
ambilWarnaDialog.show();






            }
        });








//volume fucntion

    volume=    findViewById(R.id.volume);

mute= findViewById(R.id.mute);
















        //notification section
             String msg="i m fb msg";

        FirebaseMessaging.getInstance().subscribeToTopic("ziker")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d(TAG, msg);

                    }
                });












//ids section



        bottomNavigationView=findViewById(R.id.bottom);
        imageOn = findViewById(R.id.imgon);
        // Retrieve count value from SharedPreferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        count = prefs.getInt(Count_key, 0);
        imgOff= findViewById(R.id.imgoff);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btnCount);

        led= findViewById(R.id.btnLed);
        rebtn = findViewById(R.id.btnReset);

        tv.setText(String.valueOf(count));

            bottomNavigationView= findViewById(R.id.bottom);






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



// button sections

        rebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                tv.setText("");
            }
        });



        final boolean[] isLed = {true};

        led.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (imgOff.getVisibility() == View.VISIBLE) {
                    imgOff.setVisibility(View.INVISIBLE);
                    imageOn.setVisibility(View.VISIBLE);
                } else {
                    imageOn.setVisibility(View.INVISIBLE);
                    imgOff.setVisibility(View.VISIBLE);
                }
            }
        });











        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                tv.setText(String.valueOf(count));


                    mediaPlayer1 = MediaPlayer.create(MainActivity.this, R.raw.effect);
                    mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                    mediaPlayer1.start();

                    if (count % 100 == 0) {
                        mediaPlayer1 = MediaPlayer.create(MainActivity.this, R.raw.notification);
                        mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                        mediaPlayer1.start();
                    }

                    if (count % 1000 == 0) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.click);
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        mediaPlayer.start();
                        if (vibrator != null) {
                            vibrator.vibrate(700);
                        }
                    }

            }
        });


       //volume on off is here


        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (mediaPlayer1 != null) {
                    mediaPlayer1.stop();
                    mediaPlayer1.release();
                    mediaPlayer1 = null;
                }





                volume.setVisibility(View.INVISIBLE);
                mute.setVisibility(View.VISIBLE);




            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer1 != null) {
                    mediaPlayer1.stop();
                    mediaPlayer1.release();
                    mediaPlayer1 = null;
                }

                volume.setVisibility(View.VISIBLE);
                mute.setVisibility(View.INVISIBLE);
            }
        });





















        //bottom menue sections

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.share) {
                    // Get the APK file of your app
                    Intent ishare = new Intent(Intent.ACTION_SEND);

                    ishare.setType("text/plain");

                    ishare.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.zikercounter&pli=1");





                    // Create a chooser intent to allow the user to choose which app to share with
                    Intent chooserIntent = Intent.createChooser(ishare, "Share via");



                    // Start the chooser activity
                    startActivity(chooserIntent);
                }


                if (item.getItemId() == R.id.like) {
                    String appPackageName = "com.zikercounter";
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }


//                if (item.getItemId() == R.id.setting) {
//
//                    Intent intent=new Intent(MainActivity.this, settings.class);
//
//                    startActivity(intent);
//
//                }
//
//
//
                if (item.getItemId() == R.id.dashboard) {

                    Intent intent=new Intent(MainActivity.this, dashboard.class);

                    startActivity(intent);

                }


                return true;
            }
        });












    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save count value to SharedPreferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Count_key, count);
        editor.apply();
    }



}
