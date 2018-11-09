package com.capriccioso.groceryapp.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.capriccioso.groceryapp.controller.AddItemFragment;
import com.capriccioso.groceryapp.controller.ListFragment;

/**
 * Created by dobit on 11/9/2018.
 */

//View that handles the order of pages
public class ItemPagerAdapter extends FragmentPagerAdapter {

    public ItemPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AddItemFragment tab1 = new AddItemFragment();
                return tab1;
            case 1:
                ListFragment tab2 = new ListFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Item";
            case 1:
                return "List";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
