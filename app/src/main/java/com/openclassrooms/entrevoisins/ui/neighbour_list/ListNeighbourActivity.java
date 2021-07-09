package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabWidget;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.VP)
    ViewPager mViewPager;
    //@BindView(R.id.tabItem2)
    //TabItem mTabItem2;

    ListNeighbourPagerAdapter mPagerAdapter;
    FavoritePagerAdapter mFavAdapter;
    //private boolean favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //mTabLayout.addOnTabSelectedListener(new View.OnClickListener(mViewPager));

        FavorisTabItem();

        //mViewPager.setAdapter(mFavAdapter);
        //mViewPager.addOnAdapterChangeListener();
    }

    /*@OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }*/

    //@Optional
    /**@OnClick(R.id.tabItem2)
    void Favorites() {
        System.out.println(" put N fav  : ");
        Toast.makeText(ListNeighbourActivity.this, "Homme", Toast.LENGTH_SHORT).show();
    }*/

    /**
     * Ecoute du bouton tabitem
     * -
     * - s'il y a bien eu click :
     * - affichage de l'onglet (favoris)
     * @param tbFav
     * @param cls
     */
    private void FavorisTabClick(TabItem tbFav, final Class cls) {
        //((TabItem) findViewById(R.id.tabItem2)).setOnClickListener(new Button.OnClickListener() {
        Toast.makeText(ListNeighbourActivity.this, "Tab 1", Toast.LENGTH_LONG).show();
        /**tbFav.setOnClickListener(new ImageButton.OnClickListener() {
        //tbFav.setOnClickListener(new TabItem().callOnClick() {
        @Override
        public void onClick(View v) {
                Toast.makeText(ListNeighbourActivity.this, "Favoris ", Toast.LENGTH_SHORT).show();
            }
        }
         );*/
    }

    public void FavorisTabItem(){
        TabItem tbFav = (TabItem) findViewById(R.id.tabItem2);
        //btBackAccueil.setImageResource(R.drawable.back);
        FavorisTabClick(tbFav, ListNeighbourActivity.class);
            }
}