package com.robosoft.archanakumari.parserassignment.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.robosoft.archanakumari.parserassignment.R;
import com.robosoft.archanakumari.parserassignment.fragments.JsonFragment;
import com.robosoft.archanakumari.parserassignment.fragments.MyFragment;
import com.robosoft.archanakumari.parserassignment.fragments.SongFragmentForRecyclerView;
import com.robosoft.archanakumari.parserassignment.fragments.SongFragmentForListView;

/**
 * Created by archanakumari on 15/12/15.
 */
public class MyPagerAdapter  extends FragmentPagerAdapter {

    private String mTabName[];

    public MyPagerAdapter(FragmentManager fragmentManager,Context context) {
        super(fragmentManager);
        mTabName = context.getResources().getStringArray(R.array.name);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabName[position];
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            SongFragmentForListView xmlFragment = new SongFragmentForListView();
            return  xmlFragment;
        } else if (position == 1){
            JsonFragment jsonFragment = new JsonFragment();
            return  jsonFragment;
        }else if (position ==2){
            SongFragmentForRecyclerView songFragment = new SongFragmentForRecyclerView();
            return  songFragment;
        }
        else {
            MyFragment myFragment = MyFragment.getInstance(position);
            return myFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
