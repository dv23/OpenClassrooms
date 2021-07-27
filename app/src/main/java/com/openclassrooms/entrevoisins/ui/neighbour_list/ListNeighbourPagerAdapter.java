package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private String[] tab;

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: //Page number 1
                return NeighbourFragment.newInstance();
            case 1: //Page number 2
                return FavoriteFragment.newInstance();
            default:
                return null;
        }
        //return NeighbourFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return (2);
        //return 1;
    }

    @Override
    public CharSequence getPageTitle(int position){
        //return tab[position];
        switch (position){
            case 0: //Page number 1
                return "voisins";
            case 1: //Page number 2
                return "fav";
            //case 2: //Page number 3
            //    return "Paramètre";
            default:
                return null;
        }

    }

}