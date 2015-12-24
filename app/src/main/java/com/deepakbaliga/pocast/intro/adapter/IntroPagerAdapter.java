package com.deepakbaliga.pocast.intro.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.deepakbaliga.pocast.intro.FragmentMinimal;
import com.deepakbaliga.pocast.intro.FragmentOffline;
import com.deepakbaliga.pocast.intro.FragmentPlaylist;
import com.deepakbaliga.pocast.intro.FragmentSubscribe;

/**
 * Created by deezdroid on 24/12/15.
 */
public class IntroPagerAdapter extends FragmentStatePagerAdapter {

    public IntroPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentMinimal();

            case 1:
                return  new FragmentSubscribe();

            case 2:
                return  new FragmentOffline();
            case 3:
                return  new FragmentPlaylist();

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}