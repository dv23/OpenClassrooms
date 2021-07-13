package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class FavoritePagerAdapter extends FragmentPagerAdapter {

    private String[] tabvpager;

    public FavoritePagerAdapter(FragmentManager fm) {
        // constructeur de l'adapteur
        super(fm);
        tabvpager = new String[]{"position1", "position2", "position3"};
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(final int position) {
        return FavoriteFragment.newInstance(tabvpager[position]);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 3;
    }
}