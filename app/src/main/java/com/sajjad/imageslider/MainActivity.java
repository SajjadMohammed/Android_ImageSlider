package com.sajjad.imageslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.sajjad.imageslider.SliderPackage.SliderPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Toolbar mainToolbar;
    TabLayout sliderTabLayout;
    ViewPager sliderPager;
    SliderPagerAdapter sliderPagerAdapter;
    List<Integer> imageResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (sliderPager.getCurrentItem() < imageResources.size() - 1) {
                            sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                        } else {
                            sliderPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 2000, 2000);
    }

    private void initViews() {
        mainToolbar=findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);

        sliderTabLayout=findViewById(R.id.sliderTabLayout);
        sliderPager=findViewById(R.id.sliderPager);
        sliderTabLayout.setupWithViewPager(sliderPager);

        initImageResources();
        sliderPagerAdapter=new SliderPagerAdapter(imageResources);
        sliderPager.setAdapter(sliderPagerAdapter);
        ZoomTransaction zoomTransaction=new ZoomTransaction();

        sliderPager.setPageTransformer(true,zoomTransaction);
    }

    private void initImageResources() {
        imageResources=new ArrayList<>();
        imageResources.add(R.drawable.headshot_copy_0);
        imageResources.add(R.drawable.ivor_knight_480px);
        imageResources.add(R.drawable.neil);
    }
}
