package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
//import com.openclassrooms.entrevoisins.repositories.NeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.view.neighbour.DetailNeighbourActivity;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.VP)
    ViewPager mViewPager;

    private NeighbourApiService mApiService;

    ListNeighbourPagerAdapter mPagerAdapter;
    //FavoritePagerAdapter mFavAdapter;
    //private boolean favorite;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        // configure fragment dynamique
        //Set Adapter PageAdapter and glue it together
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        /**mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));*/

        //Get TabLayout from layout
        TabLayout tabs= (TabLayout)findViewById(R.id.tabs);
        //Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(mViewPager);
        //Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);

        mApiService = DI.getNeighbourApiService();
        mApiService.getFavoriteNeighbours().clear();

        //initSelection();
    }

    /**public void initSelection() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // assuming users is at tab index 0 and favorites is at tab Index 1
                if (tab.getPosition() == 1) {
                    //selectFavorites();
                } else {
                    //selectUsers();
                    // configure fragment dynamique
                    System.out.println(" List Neighbour ontabselected :" +mApiService.getNeighbours().toString());
                    mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
                    mViewPager.setAdapter(mPagerAdapter);
                    mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
                    mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // if you wish to select the users tab at 0 by default
        Objects.requireNonNull(mTabLayout.getTabAt(0)).select();
    }*/

    /**public void isUserFavorite() {
        mFavorites = new ArrayList<>();
        for (Neighbour user : mApiService.getNeighbours()) {
            if (user.isFavorite()) {
                mFavorites.add(user);
            }
        }
    }*/

    /**private void selectFavorites() {
        Toast.makeText(ListNeighbourActivity.this, "select Favoris", Toast.LENGTH_LONG).show();
        //NeighbourRepository.
        mApiService.getFavoriteNeighbours();
        System.out.println(" List Neighbour Favorit Neighbour :" +mApiService.getFavoriteNeighbours().toString());
        //mFavAdapter = new FavoritePagerAdapter(getSupportFragmentManager());
        //mViewPager2.setAdapter(mFavAdapter);
        //mViewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }*/

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int favorite = data.getIntExtra(DetailNeighbourActivity.BUNDLE_EXTRA_FAVORI, 0);
            System.out.println(" on Activity result Neighbour  :" + favorite);
            //NRepository.getFavoriteNeighbours().clear();
            //FavoritFragment fragment = (FavoritFragment) getFragmentManager().findFragmentById(R.id.titleStrip);

            //getFragmentManager().findFragmentById(R.id.titleStrip);
            //getFragmentManager().findFragmentById(R.id.VP2);
            getFragmentManager().findFragmentById(R.id.VP);
            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get(favorite));
            //NRepository.getNeighbours().get(favorite);
            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get((int)(mIdl - 1)));
        }
    }

}