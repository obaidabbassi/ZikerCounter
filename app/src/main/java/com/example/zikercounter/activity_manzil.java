package com.example.zikercounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class activity_manzil extends AppCompatActivity {
ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manzil);


        viewPager=findViewById(R.id.viewPager);

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.man_1);
        imageList.add(R.drawable.man_2);
imageList.add(R.drawable.man_3);

        imageList.add(R.drawable.man_4);

        imageList.add(R.drawable.man_5);
        imageList.add(R.drawable.man_6);

        imageList.add(R.drawable.man_7);

        imageList.add(R.drawable.man_8);
        imageList.add(R.drawable.man_9);
        imageList.add(R.drawable.man_10);
        imageList.add(R.drawable.man_11);

        imageList.add(R.drawable.man_12);
        imageList.add(R.drawable.man_13);




        Collections.reverse(imageList);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(12, true);



    }
}

