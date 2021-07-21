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
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repositories.NeighbourRepository;
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
    //@BindView((R.id.titleStrip)
    //PagerTitleStrip mPstrip;
    //@BindView(R.id.tabItem)
    //TabItem mTabItemNeighbours;
    //@BindView(R.id.tabItem2)
    //TabItem mTabItemFavorites;
    //FloatingActionButton fav;
    @BindView(R.id.titleStrip)
    PagerTitleStrip mTitleStrip;

    private NeighbourApiService mApiService;

    ListNeighbourPagerAdapter mPagerAdapter;
    FavoritePagerAdapter mFavAdapter;
    //private boolean favorite;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    //private Object NeighbourRepository;
    private NeighbourRepository userRepository;
    private ArrayList<Neighbour> mFavorites;
    //NeighbourRepository NRepository = (com.openclassrooms.entrevoisins.repositories.NeighbourRepository) DI.getNeighbourApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        //userRepository= DI.getNewInstanceApiService();
        setSupportActionBar(mToolbar);
        // configure fragment dynamique
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mApiService = DI.getNeighbourApiService();
        //NeighbourRepository NRepository = (com.openclassrooms.entrevoisins.repositories.NeighbourRepository) DI.getNeighbourApiService();
        initSelection();
    }

    public void initSelection() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // assuming users is at tab index 0 and favorites is at tab Index 1
                if (tab.getPosition() == 1) {
                    selectFavorites();
                } else {
                    //selectUsers();
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
    }

    public void isUserFavorite() {
        mFavorites = new ArrayList<>();
        for (Neighbour user : mApiService.getNeighbours()) {
            if (user.isFavorite()) {
                mFavorites.add(user);
            }
        }
    }

    private void selectFavorites() {
        // whichever adapter you were using to show favorites
        //FavoritePagerAdapter mFavAdapter = new FavoritePagerAdapter(mFavorites, this);
        NeighbourAdapter mFavAdapter = new NeighbourAdapter(this, mFavorites);
        //NeighbourAdapter mFavAdapter = new NeighbourAdapter(getSupportFragmentManager(), mFavorites);
        //mPageOnglet.setadapter(NeighbourAdapter);
        //mTitleStrip.setAdapter(mFavAdapter);
        //mTitleStrip.setOnClickListener();
        mFavAdapter.notifyDataSetChanged();
        Toast.makeText(ListNeighbourActivity.this, "select Favoris", Toast.LENGTH_LONG).show();
        //mFavAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }

    /**@OnClick(R.id.tabItem2)
    void Favorites() {
        System.out.println(" put N fav  : ");
        Toast.makeText(ListNeighbourActivity.this, "TabFAvoris", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int favorite = data.getIntExtra(DetailNeighbourActivity.BUNDLE_EXTRA_FAVORI, 0);
            System.out.println(" on Activity result Neighbour  :" + favorite);
            //NRepository.getFavoriteNeighbours().clear();
            //FavoritFragment fragment = (FavoritFragment) getFragmentManager().findFragmentById(R.id.titleStrip);

            getFragmentManager().findFragmentById(R.id.titleStrip);

            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get(favorite));
            //NRepository.getNeighbours().get(favorite);
            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get((int)(mIdl - 1)));
        }
    }

}