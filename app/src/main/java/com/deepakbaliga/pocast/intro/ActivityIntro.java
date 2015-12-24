package com.deepakbaliga.pocast.intro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.davidpacioianu.inkpageindicator.InkPageIndicator;
import com.deepakbaliga.pocast.R;
import com.deepakbaliga.pocast.intro.adapter.IntroPagerAdapter;

public class ActivityIntro extends FragmentActivity {

    private ViewPager introViewPager;
    private PagerAdapter pagerAdapter;
    private InkPageIndicator inkPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();

    }

    private void init() {

        introViewPager = (ViewPager) findViewById(R.id.viewpager_intro);
        inkPageIndicator = (InkPageIndicator) findViewById(R.id.inkpageindicator_intro);

        pagerAdapter =  new IntroPagerAdapter(getSupportFragmentManager());
        introViewPager.setAdapter(pagerAdapter);
        inkPageIndicator.setViewPager(introViewPager);
    }

    

}
