package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.repositories.NeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.view.neighbour.DetailNeighbourActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private NeighbourApiService mApiService;
    ListNeighbourPagerAdapter mPagerAdapter;
    FavoritePagerAdapter mFavAdapter;
    //private boolean favorite;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private Object NeighbourRepository;
    //mViewPager.setAdapter(mFavAdapter);
    private NeighbourRepository userRepository;
    //NeighbourRepository NRepository = (com.openclassrooms.entrevoisins.repositories.NeighbourRepository) DI.getNeighbourApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        //NeighbourRepository NRepository = (com.openclassrooms.entrevoisins.repositories.NeighbourRepository) DI.getNeighbourApiService();
        //userRepository= DI.getNewInstanceApiService();
        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //mTabLayout.addOnTabSelectedListener(new View.OnClickListener(mViewPager));

        FavorisTabItem();

        mApiService = DI.getNeighbourApiService();
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
        getFragmentManager().findFragmentById(R.id.VP);
        //mApiService.getFavoriteNeighbours();
        //NRepository.getFavoriteNeighbours();
        //System.out.println(" Test favorit tab click  :" + NRepository.getNeighbours().size());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int favorite = data.getIntExtra(DetailNeighbourActivity.BUNDLE_EXTRA_FAVORI, 0);
            System.out.println(" on Activity result Neighbour  :" + favorite);
            //NRepository.getFavoriteNeighbours().clear();
            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get(favorite));
            //NRepository.getNeighbours().get(favorite);

            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get((int)(mIdl - 1)));
        }
    }

}